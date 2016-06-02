package me.mdbell;

import me.mdbell.jag.archive.ArchiveEntry;
import me.mdbell.jag.archive.ArchiveInputStream;
import me.mdbell.jag.cache.CacheFile;
import me.mdbell.jag.cache.CacheFileSystem;
import me.mdbell.jag.config.IndexDecoder;
import me.mdbell.jag.config.obj.RsObjectDecoder;
import me.mdbell.jag.util.Utils;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

/**
 * Created by matthew on 5/11/16.
 */
public class ObjectEntry {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Invalid usage...");
            return;
        }
        File inputDirectory = new File(args[0]);

        if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
            System.err.println(inputDirectory.getAbsolutePath() + " Doesn't exist, or isn't a directory!");
            return;
        }

        File dataFile = new File(inputDirectory, "main_file_cache.dat");

        if (!dataFile.exists()) {
            System.err.println("Data file doesn't exist!");
            return;
        }

        CacheFileSystem fs = new CacheFileSystem(FileChannel.open(dataFile.toPath(), StandardOpenOption.WRITE, StandardOpenOption.READ));
        File idxFile = new File(inputDirectory, "main_file_cache.idx0");

        FileChannel c = FileChannel.open(idxFile.toPath());
        CacheFile[] files = Utils.readFiles(c);
        c.close();

        ByteBuffer data = null, indices = null;

        //config archive
        InputStream in = fs.getInputStream(files[2]);
        if (in.available() > 0) {
            ArchiveInputStream arc = new ArchiveInputStream(in);
            ArchiveEntry entry;
            while ((entry = arc.next()) != null) {
                if (entry.getHash() == Utils.getArchiveHash("loc.dat")) {
                    data = ByteBuffer.wrap(IOUtils.toByteArray(arc));
                } else if (entry.getHash() == Utils.getArchiveHash("loc.idx")) {
                    indices = ByteBuffer.wrap(IOUtils.toByteArray(arc));
                }
            }
        }
        in.close();

        IndexDecoder indexDecoder = new IndexDecoder(indices);
        RsObjectDecoder decoder = new RsObjectDecoder(data, indexDecoder.decode());
        for (int id = 0; id < decoder.getTotal(); id++) {
            System.out.println(decoder.forId(id));
        }
        System.out.println(decoder.forId(500));
    }
}
