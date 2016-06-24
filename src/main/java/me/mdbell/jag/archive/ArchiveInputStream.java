package me.mdbell.jag.archive;

import me.mdbell.jag.util.BZ2HeaderInputStream;
import me.mdbell.jag.util.Utils;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import static me.mdbell.jag.util.Utils.readInt;
import static me.mdbell.jag.util.Utils.readTriInt;

/**
 * Created by matthew on 5/7/16.
 */
public class ArchiveInputStream extends FilterInputStream {

    private int fileCount;
    private int decompressed, compressed;

    private ArchiveEntry[] files;
    private int pos = 0;
    private ArchiveEntry current;

    private InputStream readStream;

    private int read = 0;

    public ArchiveInputStream(InputStream in) throws IOException {
        super(in);
        readHeader();
    }

    @Override
    public int read() throws IOException {
        if (current == null) {
            return -1;
        }

        int i = readStream.read();
        if (i != -1) {
            read++;
        }
        return i;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {

        int read;
        for (read = 0; read < len; read++) {
            int i = read();
            if (i == -1) {
                if (read == 0) {
                    return -1;
                } else {
                    break;
                }
            }
            b[read] = (byte) i;
        }
        this.read += read;
        return read;
    }

    public ArchiveEntry next() throws IOException {
        if (current != null) {
            closeEntry();
        }
        pos++;

        if (pos >= files.length) {
            current = null;
            readStream = null;
        } else {
            current = files[pos];
            if (current.getDecompressed() != current.getCompressed()) {
                readStream = new BZip2CompressorInputStream(new BZ2HeaderInputStream(in));
            } else {
                readStream = in;
            }
        }

        return current;
    }

    public void closeEntry() throws IOException {
        if (current == null) {
            return;
        }

        while (read() != -1) ;

        read = 0;
        current = null;
    }

    private void readHeader() throws IOException {
        decompressed = readTriInt(in);
        compressed = readTriInt(in);

        if (decompressed > compressed) {
            in = new BZip2CompressorInputStream(new BZ2HeaderInputStream(in));
        }
        fileCount = Utils.readUShort(in);

        files = new ArchiveEntry[fileCount];

        for (int i = 0; i < fileCount; i++) {
            int hash = readInt(in);
            int decomp = readTriInt(in);
            int comp = readTriInt(in);
            files[i] = new ArchiveEntry(hash, decomp, comp);
        }

        read = 0;
        pos = -1;
    }
}
