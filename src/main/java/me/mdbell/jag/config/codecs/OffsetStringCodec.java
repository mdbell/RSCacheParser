package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class OffsetStringCodec implements Codec<String[]> {
    @Override
    public String[] decode(DecodeContext<?, String[]> ctx, ByteBuffer source) {
        String[] value = ctx.getValue();
        if (value == null) {
            value = new String[1];
        }
        String str = Utils.readString(source);
        int pos = ctx.getOpcode() + ctx.getOffset();
        if (pos >= value.length) {
            value = grow(value, pos);
        }
        value[pos] = str;
        return value;
    }

    private String[] grow(String[] value, int pos) {
        String[] tmp = new String[pos];
        System.arraycopy(value, 0, tmp, 0, value.length);
        return tmp;
    }

    @Override
    public void encode(EncodeContext<?, String[]> ctx, ByteBuffer target) {

    }
}
