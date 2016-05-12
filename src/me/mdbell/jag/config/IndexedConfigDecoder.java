package me.mdbell.jag.config;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public abstract class IndexedConfigDecoder<T> extends ConfigDecoder<T> {
    int[] indices;

    protected IndexedConfigDecoder(ByteBuffer buffer, int[] indices) {
        super(buffer);
        this.indices = indices;
    }

    public T forId(int id) throws IOException {
        if (id >= indices.length || id < 0) {
            throw new IOException("Illegal id:" + id);
        }

        buffer.position(indices[id]);

        T res = decode();
        return res;
    }

    public int getTotal() {
        return indices.length;
    }
}
