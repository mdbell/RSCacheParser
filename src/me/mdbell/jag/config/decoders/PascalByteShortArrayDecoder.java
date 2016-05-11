package me.mdbell.jag.config.decoders;

import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.Decoder;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class PascalByteShortArrayDecoder implements Decoder<int[]>{
    @Override
    public int[] decode(DecodeContext<?, int[]> ctx, ByteBuffer source) {
        int len = Utils.readUByte(source);
        int[] res = new int[len];
        for(int i = 0; i < len;i++) {
            res[i] = Utils.readUShort(source);
        }
        return res;
    }

    @Override
    public void encode(int[] o, ByteBuffer target) {
//TODO write
    }
}
