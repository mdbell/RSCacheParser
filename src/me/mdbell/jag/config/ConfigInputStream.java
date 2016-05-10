package me.mdbell.jag.config;

import me.mdbell.jag.util.Utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by matthew on 5/8/16.
 */
public abstract class ConfigInputStream<T> extends FilterInputStream{

    private int total = 0;
    private int remaining = 0;

    protected ConfigInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        //readHeader();
    }

    private void readHeader() throws IOException {
        total = remaining = Utils.readUShort(in);
    }

    protected abstract OpcodeDecoder<T> getDecoder(int opcode);

    protected abstract T createTarget();

    public T next() throws IOException {

        if(in.available() == 0) {
            return null;
        }

        T res = createTarget();
        int op;
        while((op = in.read()) > 0) {
            OpcodeDecoder<T> decoder = getDecoder(op);
            if(decoder == null) {
                throw new IOException(String.format("Unhandled opcode %s on: %s",op,res.getClass().getName()));
            }
            decoder.decode(in,res);
        }
        if(op == -1) {
            return null;
        }
        remaining--;
        return res;
    }
}
