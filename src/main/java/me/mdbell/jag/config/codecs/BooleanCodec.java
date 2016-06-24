package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class BooleanCodec implements Codec<Boolean> {
    @Override
    public Boolean decode(DecodeContext<?, Boolean> ctx, ByteBuffer source) {
        return Utils.readUByte(source) != 0;
    }

    @Override
    public void encode(EncodeContext<?, Boolean> ctx, ByteBuffer target) {

    }
}
