package me.mdbell;

import me.mdbell.jag.archive.ArchiveEntry;
import me.mdbell.jag.archive.ArchiveInputStream;
import me.mdbell.jag.cache.CacheFile;
import me.mdbell.jag.cache.CacheFileSystem;
import me.mdbell.jag.images.Image;
import me.mdbell.jag.images.ImageDecoder;
import me.mdbell.jag.util.Utils;
import org.apache.commons.compress.utils.IOUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

/**
 * Created by matthew on 5/12/16.
 */
public class ImageEntry {

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

        ByteBuffer index = null, data = null;

        //title archive
        InputStream in = fs.getInputStream(files[1]);
        if (in.available() > 0) {
            ArchiveInputStream arc = new ArchiveInputStream(in);
            ArchiveEntry entry;
            while ((entry = arc.next()) != null) {
                if (entry.getHash() == Utils.getArchiveHash("index.dat")) {
                    index = ByteBuffer.wrap(IOUtils.toByteArray(arc));
                } else if (entry.getHash() == Utils.getArchiveHash("logo.dat")) {
                    data = ByteBuffer.wrap(IOUtils.toByteArray(arc));
                }
            }
        }
        in.close();
        ImageDecoder decoder = new ImageDecoder(data, index);
        Image i = decoder.decode(0);
        System.out.println(i);
        ImageIO.write(i.toBufferedImage(), "PNG", new File("./out.png"));
    }
}
