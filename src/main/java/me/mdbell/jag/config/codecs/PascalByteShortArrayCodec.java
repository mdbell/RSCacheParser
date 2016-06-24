package me.mdbell.jag.config.codecs;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class PascalByteShortArrayCodec implements Codec<int[]> {
    @Override
    public int[] decode(DecodeContext<?, int[]> ctx, ByteBuffer source) {
        int len = Utils.readUByte(source);
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = Utils.readUShort(source);
        }
        return res;
    }

    @Override
    public void encode(EncodeContext<?, int[]> ctx, ByteBuffer target) {
//TODO write
    }
}
