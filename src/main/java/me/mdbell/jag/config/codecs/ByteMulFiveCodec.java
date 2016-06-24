package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class ByteMulFiveCodec extends ByteCodec {

    @Override
    public Integer decode(DecodeContext<?, Integer> ctx, ByteBuffer buffer) {
        return super.decode(ctx, buffer) * 5;
    }

    @Override
    public void encode(EncodeContext<?, Integer> ctx, ByteBuffer target) {
        target.put((byte) (ctx.getValue() / 5));
    }
}
