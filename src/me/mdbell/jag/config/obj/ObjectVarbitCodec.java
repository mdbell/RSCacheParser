package me.mdbell.jag.config.obj;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class ObjectVarbitCodec implements Codec<Integer> {
    @Override
    public Integer decode(DecodeContext<?, Integer> ctx, ByteBuffer source) {
        RsObject n = (RsObject) ctx.getTarget();
        n.varBitId = Utils.readUShort(source);
        n.sessionSettingId = Utils.readUShort(source);
        int len = Utils.readUByte(source);
        n.childrenIds = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            n.childrenIds[i] = Utils.readUShort(source);
        }
        return n.varBitId;
    }

    @Override
    public void encode(EncodeContext<?, Integer> ctx, ByteBuffer target) {

    }
}
