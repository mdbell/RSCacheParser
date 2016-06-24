package me.mdbell.jag.config;

import java.lang.reflect.Field;

/**
 * Created by matthew on 5/11/16.
 */
public class FieldContext {

    private Field field;
    private Codec<?> codec;

    FieldContext(Field field, Codec<?> codec) {
        this.field = field;
        this.codec = codec;
    }

    public Field getField() {
        return field;
    }

    public Codec<?> getCodec() {
        return codec;
    }
}
