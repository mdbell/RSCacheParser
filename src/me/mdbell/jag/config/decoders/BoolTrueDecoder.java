package me.mdbell.jag.config.decoders;

import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.Decoder;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class BoolTrueDecoder implements Decoder<Boolean>{
    @Override
    public Boolean decode(DecodeContext<?, Boolean> ctx, ByteBuffer source) {
        return true;
    }

    @Override
    public void encode(Boolean o, ByteBuffer target) {

    }
}
