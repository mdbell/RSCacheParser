package me.mdbell.jag.cache;

import me.mdbell.jag.Constants;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author matt123337
 */
public final class CacheSectorInputStream extends InputStream implements Constants {

    private int pos;
    private ISector sector,first;
    private CacheFileSystem fs;

    CacheSectorInputStream(CacheFileSystem fs, ISector sector) {
        this.fs = fs;
        this.sector = first = sector;
        pos = 0;
    }

    ISector getSector() {
        return sector;
    }

    CacheFileSystem getFileSystem() {
        return fs;
    }

    @Override
    public void reset() throws IOException {
        pos = 0;
        sector = first;
    }

    @Override
    public int read() throws IOException {
        if (!updatePosition()) {
            return -1;
        }
        return sector.getByte(pos++);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (!updatePosition()) {
            return -1;
        }

        len = Math.min(SECTOR_DATA_SIZE - pos, len);
        sector.getBytes(pos, b, off, len);
        pos += len;

        return len;
    }

    private boolean updatePosition() throws IOException {
        if (pos == SECTOR_DATA_SIZE) {
            int next = sector.getSectorNum();
            if (next == NULL_SECTOR_ID) {
                return false;
            }
            ISector sec = fs.read(next);

            if (sec.getCacheNum() != sector.getCacheNum()) {
                throw new IOException(String.format("Sector CacheNum missmatch. Expected:%s Actual:%s", sector.getCacheNum(), sec.getCacheNum()));
            }

            if (sec.getFileNum() != sector.getFileNum()) {
                throw new IOException(String.format("Sector FileNum missmatch. Expected:%s Actual:%s", sector.getFileNum(), sec.getFileNum()));
            }

            if (sec.getPartNum() != sector.getPartNum() + 1) {
                throw new IOException(String.format("Sector Part missmatch. Expected:%s Actual:%s", sector.getPartNum() + 1, sec.getPartNum()));
            }

            sector = sec;
            pos = 0;
        }
        return true;
    }
}
