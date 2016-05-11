package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class ActionCodec implements Codec<String[]> {
    @Override
    public String[] decode(DecodeContext<?, String[]> ctx, ByteBuffer source) {
        String[] value = ctx.getValue();
        if(value == null) {
            value = new String[5];
        }
        value[ctx.getOpcode() - ctx.getOffset()] = Utils.readString(source);
        return value;
    }

    @Override
    public void encode(EncodeContext<?, String[]> ctx, ByteBuffer target) {

    }
}
