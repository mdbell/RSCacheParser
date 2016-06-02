package me.mdbell.jag.config;

import me.mdbell.jag.config.annotations.ArrayOffset;
import me.mdbell.jag.config.annotations.RangeSerialize;
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
    private final FieldContext[] fieldContexts = new FieldContext[256];
    protected ByteBuffer buffer;
    private boolean read = false;

    protected ConfigDecoder(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    private void parseMeta(T obj) {
        if (read) {
            return;
        }
        read = true;
        Class<?> c = obj.getClass();
        for (Field f : c.getDeclaredFields()) {
            f.setAccessible(true);
            Serialize s = f.getAnnotation(Serialize.class);
            if (s != null) {
                Codec codec;
                try {
                    codec = s.codec().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    continue;
                }
                FieldContext ctx = new FieldContext(f, codec);
                for (int op : s.opcodes()) {
                    fieldContexts[op] = ctx;
                }
            }
            RangeSerialize rs = f.getAnnotation(RangeSerialize.class);
            if (rs != null) {
                Codec codec;
                try {
                    codec = rs.codec().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    continue;
                }
                FieldContext ctx = new FieldContext(f, codec);
                for (int i = rs.min(); i <= rs.max(); i++) {
                    fieldContexts[i] = ctx;
                }
            }
        }
    }

    protected abstract T createTarget();

    protected T decode() throws IOException {
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
        FieldContext fieldCtx = fieldContexts[opcode];
        if (fieldCtx == null) {
            throw new IOException("Unhandled config opcode:" + opcode);
        }

        Field f = fieldCtx.getField();
        Codec codec = fieldCtx.getCodec();

        try {
            DecodeContext<?, ?> ctx = createCtx(res, opcode, f);
            Object value = codec.decode(ctx, buffer);
            f.set(res, value);
        } catch (IllegalAccessException ignored) {

        }
    }

    private DecodeContext<?, ?> createCtx(T res, int opcode, Field f) throws IllegalAccessException {
        Object value = f.get(res);
        DecodeContext<?, Object> ctx = new DecodeContext<>(opcode, res);

        ctx.setValue(value);

        if (f.isAnnotationPresent(ArrayOffset.class)) {
            ctx.setOffset(f.getAnnotation(ArrayOffset.class).value());
        }

        return ctx;
    }

}
