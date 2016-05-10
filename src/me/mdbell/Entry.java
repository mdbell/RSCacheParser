package me.mdbell;

import me.mdbell.jag.archive.ArchiveEntry;
import me.mdbell.jag.archive.ArchiveInputStream;
import me.mdbell.jag.cache.*;
import me.mdbell.jag.config.npc.Npc;
import me.mdbell.jag.config.npc.NpcConfigInputStream;
import me.mdbell.jag.util.Utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author matt123337
 */
public class Entry implements Constants {

    private static final List<String> ARCHIVE_FILE_NAMES = new ArrayList<>();

    static {
        try {
            File f = new File("./names.txt");
            if (f.exists()) {
                ARCHIVE_FILE_NAMES.addAll(Files.readAllLines(f.toPath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Invalid usage...");
            return;
        }
        File inputDirectory = new File(args[0]);
        File outputZip = new File(args[1]);

        if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
            System.err.println(inputDirectory.getAbsolutePath() + " Doesn't exist, or isn't a directory!");
            return;
        }

        FileOutputStream fout = new FileOutputStream(outputZip);
        ZipOutputStream zout = new ZipOutputStream(fout);

        File dataFile = new File(inputDirectory, "main_file_cache.dat");

        if (!dataFile.exists()) {
            System.err.println("Data file doesn't exist!");
            return;
        }

        CacheFileSystem fs = new CacheFileSystem(FileChannel.open(dataFile.toPath(), StandardOpenOption.WRITE, StandardOpenOption.READ));

        for (int i = 0; i < 256; i++) {
            File idxFile = new File(inputDirectory, "main_file_cache.idx" + i);

            if (!idxFile.exists()) {
                continue;
            }

            FileChannel c = FileChannel.open(idxFile.toPath());
            CacheFile[] files = Utils.readFiles(c);
            c.close();

            for (int j = 0; j < files.length; j++) {
                String name = i + "/" + j;
                if (i < INDEX_NAMES.length) {
                    name = INDEX_NAMES[i] + "/" + j;
                }

                InputStream in = fs.getInputStream(files[j]);
                if (i == 0) {
                    if (in.available() > 0) {
                        name = INDEX_NAMES[i] + "/" + ARCHIVE_NAMES[j];
                        dumpArchive(in, name, zout);
                    }
                } else {
                    dumpFile(name, zout, in);
                }
                in.close();

            }
        }
        zout.close();
    }

    private static void dumpArchive(InputStream in, String prefix, ZipOutputStream zout) throws IOException {
        prefix = prefix + '/';
        System.out.println("Dumping archive:" + prefix);
        ArchiveInputStream arc = new ArchiveInputStream(in);
        ArchiveEntry entry;
        while ((entry = arc.next()) != null) {
            String name = prefix + getNameFromHash(entry.getHash());
            if(name.endsWith("npc.dat")) {
                NpcConfigInputStream config = new NpcConfigInputStream(arc);
                Npc n;
                while((n = config.next()) != null) {
                    System.out.println(n);
                }
                System.exit(0);
            }
            dumpFile(name, zout, arc);
        }
    }

    private static void dumpFile(String name, ZipOutputStream zout, InputStream in) throws IOException {
        ZipEntry entry = new ZipEntry(name);
        zout.putNextEntry(entry);
        System.out.println("Dumping file:" + entry.getName());
        dump(in, zout);
    }

    private static void dump(InputStream in, OutputStream os) throws IOException {

        byte[] b = new byte[100];
        int read;
        while ((read = in.read(b)) != -1) {
            os.write(b, 0, read);
        }
    }

    public static String getNameFromHash(int hash) {
        for (String str : ARCHIVE_FILE_NAMES) {
            if (Utils.getArchiveHash(str) == hash) {
                return str;
            }
        }
        return String.format("unk_%08X", hash);
    }
}
