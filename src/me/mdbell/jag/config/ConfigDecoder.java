package me.mdbell.jag.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public abstract class ConfigDecoder<T> {

    private ByteBuffer buffer;

    private final Field[] fields = new Field[256];
    private final Decoder[] decoders = new Decoder[256];
    private boolean read = false;

    protected ConfigDecoder(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    private void parseMeta(T obj) {
        if(read) {
            return;
        }
        read = true;
        Class<?> c = obj.getClass();
        for(Field f : c.getDeclaredFields()) {
            Serialize s = f.getAnnotation(Serialize.class);
            if(s == null) {
                continue;
            }
            f.setAccessible(true);
            Decoder decoder = null;
            try {
                decoder = s.decoder().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            for(int op : s.opcodes()) {
                fields[op] = f;
                decoders[op] = decoder;
            }
        }
    }

    protected  abstract T create();

    public T decode(IPosition position) throws IOException {
        if(position.getPosition() > buffer.capacity()) {
            return null;
        }
        buffer.position(position.getPosition());

        T res = create();
        parseMeta(res);

        int opcode;
        while((opcode = buffer.get() & 0xFF) != 0) {
            Field f = fields[opcode];
            Decoder d = decoders[opcode];
            if(f == null || d == null) {
                throw new IOException("Unhandled config opcode:" + opcode);
            }
            try {
                Object value = f.get(res);
                f.set(res,d.decode(opcode, value, buffer));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
