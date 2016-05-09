package me.mdbell.jag.cache;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author matt123337
 */
public class CacheSectorOutputStream extends OutputStream{

    private ArraySector first;
    private ArraySector sector;

    private CacheFileSystem fs;

    CacheSectorOutputStream(CacheFileSystem fs) {

    }

    @Override
    public void write(int b) throws IOException {

    }
}
