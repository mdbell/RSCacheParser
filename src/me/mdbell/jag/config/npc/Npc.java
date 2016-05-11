package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.annotations.ArrayOffset;
import me.mdbell.jag.config.annotations.RangeSerialize;
import me.mdbell.jag.config.annotations.Serialize;
import me.mdbell.jag.config.codecs.*;

import java.util.Arrays;

/**
 * Created by matthew on 5/8/16.
 */
public class Npc {

    @Serialize(opcodes = 1, codec = PascalByteShortArrayCodec.class)
    int[] models;
    @Serialize(opcodes = 2, codec = StringCodec.class)
    String name;
    @Serialize(opcodes = 3, codec = StringCodec.class)
    String desc;

    @Serialize(opcodes = 12, codec = ByteCodec.class)
    int boundDim = 1;
    @Serialize(opcodes = 13, codec = ShortCodec.class)
    int idleAnimation = -1;
    @Serialize(opcodes = 14, codec = ShortCodec.class)
    int walkAnimIndex = -1;

    @Serialize(opcodes = 17, codec = AnimationRotationCodec.class)
    int turn180Index = -1;
    int turn90CWAnimIndex = -1;
    int turn90CCWAnimIndex = -1;

    @ArrayOffset(-30)
    @RangeSerialize(min = 30, max = 39, codec = OffsetStringCodec.class)
    String[] actions = new String[10];

    @Serialize(opcodes = 40, codec = RecolorCodec.class)
    int[] recolorOriginal;
    int[] recolorTarget;

    @Serialize(opcodes = 60, codec = PascalByteShortArrayCodec.class)
    int[] additionalModels;

    @Serialize(opcodes = 93, codec = BoolFalseCodec.class)
    boolean showOnMinimap = true;

    @Serialize(opcodes = 95, codec = ShortCodec.class)
    int combatLevel = -1;

    @Serialize(opcodes = 97, codec = ShortCodec.class)
    int scaleXZ = 128;
    @Serialize(opcodes = 98, codec = ShortCodec.class)
    int scaleY = 128;
    @Serialize(opcodes = 99, codec = BoolTrueCodec.class)
    boolean invisible = false;
    @Serialize(opcodes = 100, codec = ByteCodec.class)
    int lightModifier;
    @Serialize(opcodes = 101, codec = ByteMulFiveCodec.class)
    int shadowModifier;
    @Serialize(opcodes = 102, codec = ShortCodec.class)
    int headIcon = -1;
    @Serialize(opcodes = 103, codec = ShortCodec.class)
    int degreesToTurn = 32;

    @Serialize(opcodes = 106, codec = VarbitCodec.class)
    int varBitId = -1;
    int sessionSettingId = -1;
    int[] childrenIds;
    @Serialize(opcodes = 107, codec = BoolFalseCodec.class)
    boolean clickable = true;

    @Override
    public String toString() {
        return "Npc{" +
                "models=" + Arrays.toString(models) +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", boundDim=" + boundDim +
                ", idleAnimation=" + idleAnimation +
                ", walkAnimIndex=" + walkAnimIndex +
                ", turn180Index=" + turn180Index +
                ", turn90CWAnimIndex=" + turn90CWAnimIndex +
                ", turn90CCWAnimIndex=" + turn90CCWAnimIndex +
                ", actions=" + Arrays.toString(actions) +
                ", recolorOriginal=" + Arrays.toString(recolorOriginal) +
                ", recolorTarget=" + Arrays.toString(recolorTarget) +
                ", additionalModels=" + Arrays.toString(additionalModels) +
                ", showOnMinimap=" + showOnMinimap +
                ", combatLevel=" + combatLevel +
                ", scaleXZ=" + scaleXZ +
                ", scaleY=" + scaleY +
                ", invisible=" + invisible +
                ", lightModifier=" + lightModifier +
                ", shadowModifier=" + shadowModifier +
                ", headIcon=" + headIcon +
                ", degreesToTurn=" + degreesToTurn +
                ", varBitId=" + varBitId +
                ", sessionSettingId=" + sessionSettingId +
                ", childrenIds=" + Arrays.toString(childrenIds) +
                ", clickable=" + clickable +
                '}';
    }
}
