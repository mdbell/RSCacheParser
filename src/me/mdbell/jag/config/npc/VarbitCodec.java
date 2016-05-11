package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.Codec;
import me.mdbell.jag.config.DecodeContext;
import me.mdbell.jag.config.EncodeContext;
import me.mdbell.jag.util.Utils;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class VarbitCodec implements Codec<Integer> {
    @Override
    public Integer decode(DecodeContext<?, Integer> ctx, ByteBuffer source) {
        Npc n = (Npc) ctx.getTarget();
        n.varBitId = parseShort(source);
        n.sessionSettingId = parseShort(source);
        int len = Utils.readUByte(source);
        n.childrenIds = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            n.childrenIds[i] = parseShort(source);
        }
        return n.varBitId;
    }

    private int parseShort(ByteBuffer source) {
        int res = Utils.readUShort(source);
        if (res == 65535) {
            res = -1;
        }
        return res;
    }

    @Override
    public void encode(EncodeContext<?, Integer> ctx, ByteBuffer target) {

    }
}
