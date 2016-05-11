package me.mdbell.jag.config.decoders;

import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.Decoder;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class StringDecoder implements Decoder<String> {
    @Override
    public String decode(DecodeContext<?, String> ctx, ByteBuffer source) {
        return Utils.readString(source);
    }

    @Override
    public void encode(String o, ByteBuffer target) {
        //TODO write
    }
}
