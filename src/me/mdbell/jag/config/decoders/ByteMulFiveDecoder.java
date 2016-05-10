package me.mdbell.jag.config.decoders;

import me.mdbell.jag.config.DecodeContext;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class ByteMulFiveDecoder extends ByteDecoder {

    @Override
    public Integer decode(DecodeContext<Integer> ctx, ByteBuffer buffer) {
        return super.decode(ctx, buffer) * 5;
    }

    @Override
    public void encode(Integer o, ByteBuffer target) {
        super.encode(o / 5, target);
    }
}
