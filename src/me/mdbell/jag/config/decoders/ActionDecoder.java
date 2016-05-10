package me.mdbell.jag.config.decoders;

import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.Decoder;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class ActionDecoder implements Decoder<String[]>{
    @Override
    public String[] decode(DecodeContext<String[]> ctx, ByteBuffer source) {
        String[] value = ctx.getValue();
        if(value == null) {
            value = new String[5];
        }
        value[ctx.getOpcode() - ctx.getOffset()] = Utils.readString(source);
        return value;
    }

    @Override
    public void encode(String[] value, ByteBuffer target) {

    }
}
