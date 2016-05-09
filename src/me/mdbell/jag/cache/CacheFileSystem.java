package me.mdbell.jag.cache;

import me.mdbell.jag.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author matt123337
 */
public class CacheFileSystem implements Constants {

    FileChannel channel;

    public CacheFileSystem(FileChannel channel) {
        this.channel = channel;
    }

    public InputStream getInputStream(CacheFile file) throws IOException {
        ISector sector = read(file.getSector());
        CacheSectorInputStream sectorIn = getSectorInputStream(sector);
        return new CacheFileInputStream(file.getSize(), sectorIn);
    }

    private CacheSectorInputStream getSectorInputStream(ISector sector) {
        return new CacheSectorInputStream(this, sector);
    }

    MappedSector map(int idx) throws IOException {
        long pos = idx * SECTOR_TOTAL_SIZE;
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, pos, SECTOR_TOTAL_SIZE);
        return new MappedSector(buffer);
    }

    int getNextSector() throws IOException {
        return (int) (channel.size() / SECTOR_TOTAL_SIZE + 1);
    }

    ArraySector read(int idx) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(SECTOR_TOTAL_SIZE);
        long pos = idx * SECTOR_TOTAL_SIZE;

        Utils.readFully(channel,pos,buffer);

        buffer.flip(); // switch to reading

        int fileNum = Utils.readUShort(buffer);
        int partNum = Utils.readUShort(buffer);
        int sectorNum = Utils.readTriInt(buffer);
        int cacheIdx = Utils.readUByte(buffer);
        byte[] data = new byte[SECTOR_DATA_SIZE];
        buffer.get(data);

        return new ArraySector(fileNum, partNum, sectorNum, cacheIdx, data);
    }

}
