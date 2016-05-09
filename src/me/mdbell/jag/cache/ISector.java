package me.mdbell.jag.cache;

/**
 * @author matt123337
 */
public interface ISector {

    int getFileNum();
    int getPartNum();
    int getSectorNum();
    int getCacheNum();
    int getByte(int i);
    void getBytes(int pos, byte[] b, int off, int len);

}
