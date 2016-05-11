package me.mdbell.jag.config;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public interface Decoder<T> {

    T decode(DecodeContext<?, T> ctx, ByteBuffer source);

    void encode(T value, ByteBuffer target);
}
