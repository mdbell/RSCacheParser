package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class StringCodec implements Codec<String> {
    @Override
    public String decode(DecodeContext<?, String> ctx, ByteBuffer source) {
        return Utils.readString(source);
    }

    @Override
    public void encode(EncodeContext<?, String> ctx, ByteBuffer target) {
        //TODO write
    }
}
