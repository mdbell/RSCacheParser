package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class BoolFalseCodec implements Codec<Boolean> {
    @Override
    public Boolean decode(DecodeContext<?, Boolean> ctx, ByteBuffer source) {
        return false;
    }

    @Override
    public void encode(EncodeContext<?, Boolean> ctx, ByteBuffer target) {

    }
}
