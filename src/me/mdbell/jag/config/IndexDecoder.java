package me.mdbell.jag.config;

import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class IndexDecoder {

    private ByteBuffer buffer;

    public IndexDecoder(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public int[] decode() {
        buffer.position(0); // reset position
        int totalSize = Utils.readUShort(buffer);
        int[] res = new int[totalSize];
        int pos = buffer.position();
        for (int i = 0; i < totalSize; i++) {
            res[i] = pos;
            pos += Utils.readUShort(buffer);
        }
        return res;
    }
}
