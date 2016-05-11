package me.mdbell.jag.cache;

import java.io.FilterInputStream;
import java.io.IOException;

/**
 * @author matt123337
 */
public class CacheFileInputStream extends FilterInputStream {

    private final int size;
    private int read;

    CacheFileInputStream(int size, CacheSectorInputStream in) {
        super(in);
        this.size = size;
        read = 0;
    }

    @Override
    public void reset() throws IOException {
        in.reset();
        read = 0;
    }

    @Override
    public int available(){
        return size - read;
    }

    @Override
    public int read() throws IOException {

        if(read >= size) {
            return -1;
        }

        int i = in.read();
        if(i != -1) {
            read++;
        }
        return i;
    }

    @Override
    public int read(byte[] b,
                    int off,
                    int len)
            throws IOException {
        //check if full file has been read
        if(read >= size) {
            return -1;
        }
        //check if we're reading more then the file size, if so, trim
        if(read + len >= size) {
            len = size - read;
        }

        // read shit
        int i = in.read(b,off,len);

        //update amount of bytes read
        if(i != -1) {
            read += i;
        }
        return i;
    }
}
