package me.mdbell.jag.config.obj;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class ObjectModelCodec implements Codec<int[]> {
    @Override
    public int[] decode(DecodeContext<?, int[]> ctx, ByteBuffer source) {
        int len = Utils.readUByte(source);
        RsObject object = (RsObject) ctx.getTarget();
        object.modelIndices = new int[len];
        object.modelTypes = new int[len];
        for (int i = 0; i < len; i++) {
            object.modelIndices[i] = Utils.readUShort(source);
            object.modelTypes[i] = Utils.readUByte(source);
        }
        return object.modelIndices;
    }

    @Override
    public void encode(EncodeContext<?, int[]> ctx, ByteBuffer target) {

    }
}
