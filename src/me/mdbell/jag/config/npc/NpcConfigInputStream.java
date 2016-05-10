package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.ConfigInputStream;
import me.mdbell.jag.config.OpcodeDecoder;

import java.io.IOException;
import java.io.InputStream;

import static me.mdbell.jag.util.Utils.*;

/**
 * Created by matthew on 5/8/16.
 */
public class NpcConfigInputStream extends ConfigInputStream<Npc> {

    private static final OpcodeDecoder<Npc>[] DECODERS = new OpcodeDecoder[256];

    static {
        DECODERS[1] = (in, npc) -> {
            int len = readUByte(in);
            npc.models = new int[len];
            for (int i = 0; i < len; i++) {
                npc.models[i] = readUShort(in);
            }
        };

        DECODERS[2] = (in, npc) -> npc.name = readString(in);

        DECODERS[3] = (in, npc) -> npc.desc = readString(in);

        DECODERS[12] = (in, npc) -> npc.boundDim = readUByte(in);

        DECODERS[13] = (in, npc) -> npc.idleAnimation = readUShort(in);

        DECODERS[14] = (in, npc) -> npc.walkAnimIndex = readUShort(in);

        DECODERS[17] = (in, npc) -> {
            npc.walkAnimIndex = readUShort(in);
            npc.turn180Index = readUShort(in);
            npc.turn90CWAnimIndex = readUShort(in);
            npc.turn90CCWAnimIndex = readUShort(in);
        };
        DECODERS[30] = (in, npc) -> npc.actions[0] = readString(in);
        DECODERS[31] = (in, npc) -> npc.actions[1] = readString(in);
        DECODERS[32] = (in, npc) -> npc.actions[2] = readString(in);
        DECODERS[33] = (in, npc) -> npc.actions[3] = readString(in);
        DECODERS[34] = (in, npc) -> npc.actions[4] = readString(in);
        DECODERS[35] = (in, npc) -> npc.actions[5] = readString(in);
        DECODERS[36] = (in, npc) -> npc.actions[6] = readString(in);
        DECODERS[37] = (in, npc) -> npc.actions[7] = readString(in);
        DECODERS[38] = (in, npc) -> npc.actions[8] = readString(in);
        DECODERS[39] = (in, npc) -> npc.actions[9] = readString(in);

        DECODERS[40] = (in, npc) -> {
            int len = readUByte(in);
            npc.recolorOriginal = new int[len];
            npc.recolorTarget = new int[len];
            for (int i = 0; i < len; i++) {
                npc.recolorOriginal[i] = readUShort(in);
                npc.recolorTarget[i] = readUShort(in);
            }
        };
        DECODERS[60] = (in, npc) -> {
            int len = readUByte(in);
            npc.additionalModels = new int[len];
            for (int i = 0; i < len; i++) {
                npc.additionalModels[i] = readUShort(in);
            }
        };
        DECODERS[90] = DECODERS[91] = DECODERS[92] = (in, npc) -> readUShort(in);

        DECODERS[93] = (in, npc) -> npc.showOnMinimap = false;

        DECODERS[95] = (in, npc) -> npc.combatLevel = readUShort(in);

        DECODERS[97] = (in, npc) -> npc.scaleXZ = readUShort(in);
        DECODERS[98] = (in, npc) -> npc.scaleY = readUShort(in);
        DECODERS[99] = (in, npc) -> npc.invisible = true;
        DECODERS[100] = (in, npc) -> npc.lightModifier = readUByte(in);
        DECODERS[101] = (in, npc) -> npc.shadowModifier = readUByte(in) * 5;
        DECODERS[102] = (in, npc) -> npc.headIcon = readUShort(in);
        DECODERS[103] = (in, npc) -> npc.degreesToTurn = readUShort(in);

        DECODERS[106] = (in, npc) -> {
            npc.varBitId = readUShort(in);
            if(npc.varBitId == 65535) {
                npc.varBitId = -1;
            }
            npc.sessionSettingId = readUShort(in);
            if(npc.sessionSettingId == 65535) {
                npc.sessionSettingId = -1;
            }
            int len = readUByte(in);
            npc.childrenIds = new int[len + 1];
            for(int i = 0; i <= len;i++) {
                int id = readUShort(in);
                if(id == 65535) {
                    id = -1;
                }
                npc.childrenIds[i] = id;
            }
        };
        DECODERS[107] = (in, npc) -> {
            npc.clickable = false;
        };
    }

    public NpcConfigInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected OpcodeDecoder<Npc> getDecoder(int opcode) {
        return DECODERS[opcode];
    }

    @Override
    protected Npc createTarget() {
        return new Npc();
    }
}
