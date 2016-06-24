package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class IntegerCodec implements Codec<Integer> {
    @Override
    public Integer decode(DecodeContext<?, Integer> ctx, ByteBuffer source) {
        return Utils.readInt(source);
    }

    @Override
    public void encode(EncodeContext<?, Integer> ctx, ByteBuffer target) {
        target.putInt(ctx.getValue());
    }
}
