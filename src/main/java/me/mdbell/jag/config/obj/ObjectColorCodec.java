package me.mdbell.jag.config.obj;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class ObjectColorCodec implements Codec<int[]> {
    @Override
    public int[] decode(DecodeContext<?, int[]> ctx, ByteBuffer source) {
        RsObject obj = (RsObject) ctx.getTarget();
        int len = Utils.readUByte(source);
        obj.oldColor = new int[len];
        obj.newColor = new int[len];
        for (int i = 0; i < len; i++) {
            obj.oldColor[i] = Utils.readUShort(source);
            obj.newColor[i] = Utils.readUShort(source);
        }
        return obj.oldColor;
    }

    @Override
    public void encode(EncodeContext<?, int[]> ctx, ByteBuffer target) {

    }
}
