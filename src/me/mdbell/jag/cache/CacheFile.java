package me.mdbell.jag.cache;

/**
 * @author matt123337
 */
public class CacheFile {

    private int size;
    private int sector;

    public CacheFile(int size, int sector){
        this.size = size;
        this.sector = sector;
    }

    public int getSize(){
        return size;
    }

    public int getSector(){
        return sector;
    }
}
