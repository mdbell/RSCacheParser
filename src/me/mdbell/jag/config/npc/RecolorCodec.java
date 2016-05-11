package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class RecolorCodec implements Codec<int[]> {
    @Override
    public int[] decode(DecodeContext<?, int[]> ctx, ByteBuffer source) {
        int len = Utils.readUByte(source);
        Npc n = (Npc) ctx.getTarget();
        n.recolorOriginal = new int[len];
        n.recolorTarget = new int[len];
        for (int i = 0; i < len; i++) {
            n.recolorOriginal[i] = Utils.readUShort(source);
            n.recolorTarget[i] = Utils.readUShort(source);
        }
        return n.recolorOriginal;
    }

    @Override
    public void encode(EncodeContext<?, int[]> ctx, ByteBuffer target) {

    }
}
