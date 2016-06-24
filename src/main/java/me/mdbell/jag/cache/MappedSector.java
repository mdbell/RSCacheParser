package me.mdbell.jag.cache;

import me.mdbell.jag.Constants;

import java.nio.MappedByteBuffer;

/**
 * @author matt123337
 */
public class MappedSector implements ISector, Constants {

    private MappedByteBuffer buffer;

    public MappedSector(MappedByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public int getFileNum() {
        return buffer.getShort(0) & 0xFFFF;
    }

    @Override
    public int getPartNum() {
        return buffer.getShort(2) & 0xFFFF;
    }

    @Override
    public int getSectorNum() {
        return (buffer.get(4) & 0xFF) << 16 | (buffer.getShort(5) & 0xFFFF);
    }

    @Override
    public int getCacheNum() {
        return buffer.get(7) & 0xFF;
    }

    @Override
    public int getByte(int i) {
        return buffer.get(SECTOR_HEADER_SIZE + i);
    }

    @Override
    public void getBytes(int pos, byte[] b, int off, int len) {
        for (int i = 0; i < len; i++) {
            b[off + i] = (byte) getByte(pos + i);
        }
    }
}
