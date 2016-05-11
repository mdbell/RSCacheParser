package me.mdbell.jag.config;

import me.mdbell.jag.config.annotations.OpcodeOffset;
import me.mdbell.jag.config.annotations.Serialize;
import me.mdbell.jag.util.Utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public abstract class ConfigDecoder<T> {

    public static final int TERMINATING_OPCODE = 0;
    private final Field[] fields = new Field[256];
    private final Decoder[] decoders = new Decoder[256];
    private ByteBuffer buffer;
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

    public void setPosition(int pos) {
        buffer.position(pos);
    }

    protected abstract T createTarget();

    public T decode() throws IOException {
        if (buffer.remaining() == 0) {
            return null;
        }

        T res = createTarget();
        parseMeta(res);

        int opcode;
        while ((opcode = Utils.readUByte(buffer)) != TERMINATING_OPCODE) {
            decodeOpcode(res, opcode);
        }
        return res;
    }

    private void decodeOpcode(T res, int opcode) throws IOException {
        Field f = fields[opcode];
        Decoder d = decoders[opcode];
        if (f == null || d == null) {
            throw new IOException("Unhandled config opcode:" + opcode);
        }
        try {
            DecodeContext<T, ?> ctx = createCtx(res, opcode, f);
            Object value = d.decode(ctx, buffer);
            f.set(res, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private DecodeContext<T, ?> createCtx(T res, int opcode, Field f) throws IllegalAccessException {
        Object value = f.get(res);
        DecodeContext<T, Object> ctx = new DecodeContext<>(opcode, res);

        ctx.setValue(value);

        if (f.isAnnotationPresent(OpcodeOffset.class)) {
            ctx.setOffset(f.getAnnotation(OpcodeOffset.class).value());
        }

        return ctx;
    }

}
