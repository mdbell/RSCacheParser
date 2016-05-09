package me.mdbell.jag.cache;

import java.util.Arrays;

/**
 * @author matt123337
 */
final class ArraySector implements ISector{

    private int fileNum; // current fileNum (short)
    private int partNum; // current file part (short)
    private int sectorNum; // sector id (tri-int)
    private int cacheNum; // idx file (single byte)
    private byte[] data; // file data (512 bytes)

    ArraySector(int fileNum, int partNum, int sectorNum, int cacheNum, byte[] data){
        this.fileNum = fileNum;
        this.partNum = partNum;
        this.sectorNum = sectorNum;
        this.cacheNum = cacheNum;
        this.data = data;
    }

    public int getFileNum(){
        return fileNum;
    }

    public int getPartNum(){
        return partNum;
    }

    public int getSectorNum(){
        return sectorNum;
    }

    public int getCacheNum(){
        return cacheNum;
    }

    public int getByte(int i) {
        return data[i] & 0xFF;
    }

    @Override
    public String toString() {
        return "ArraySector{" +
                "fileNum=" + fileNum +
                ", partNum=" + partNum +
                ", sectorNum=" + sectorNum +
                ", cacheNum=" + cacheNum +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public void getBytes(int pos, byte[] b, int off, int len) {
        System.arraycopy(data,pos,b,off,len);
    }
}
