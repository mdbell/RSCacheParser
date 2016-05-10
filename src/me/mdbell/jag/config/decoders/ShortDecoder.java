package me.mdbell.jag.config.decoders;

import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.Decoder;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class ShortDecoder implements Decoder<Integer>{
    @Override
    public Integer decode(DecodeContext<Integer> ctx, ByteBuffer source) {
        return Utils.readUShort(source);
    }

    @Override
    public void encode(Integer o, ByteBuffer target) {
        //TODO write
    }
}
