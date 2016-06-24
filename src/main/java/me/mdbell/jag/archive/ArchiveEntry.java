package me.mdbell.jag.archive;

/**
 * Created by matthew on 5/8/16.
 */
public class ArchiveEntry {
    private int hash;
    private int decompressed;
    private int compressed;

    ArchiveEntry(int hash, int decompressed, int compressed) {
        this.hash = hash;
        this.decompressed = decompressed;
        this.compressed = compressed;
    }

    @Override
    public String toString() {
        return "ArchiveEntry{" +
                "hash=" + hash +
                ", decompressed=" + decompressed +
                ", compressed=" + compressed +
                '}';
    }

    public int getHash() {
        return hash;
    }

    public int getCompressed() {
        return compressed;
    }

    public int getDecompressed() {
        return decompressed;
    }
}
