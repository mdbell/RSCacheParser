package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.annotations.OpcodeOffset;
import me.mdbell.jag.config.annotations.Serialize;
import me.mdbell.jag.config.decoders.*;

import java.util.Arrays;

/**
 * Created by matthew on 5/8/16.
 */
public class Npc {

    @Serialize(opcodes = 1, decoder = PascalByteShortArrayDecoder.class)
    int[] models;
    @Serialize(opcodes = 2, decoder = StringDecoder.class)
    String name;
    @Serialize(opcodes = 3, decoder = StringDecoder.class)
    String desc;

    @Serialize(opcodes = 12, decoder = ByteDecoder.class)
    int boundDim;
    @Serialize(opcodes = 13, decoder = ShortDecoder.class)
    int idleAnimation;
    @Serialize(opcodes = 14, decoder = ShortDecoder.class)
    int walkAnimIndex;

    @Serialize(opcodes = 17, decoder = AnimationRotationDecoder.class)
    int turn180Index;
    int turn90CWAnimIndex;
    int turn90CCWAnimIndex;

    @OpcodeOffset(30)
    @Serialize(opcodes = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39}, decoder = OffsetStringDecoder.class)
    String[] actions = new String[10];

    @Serialize(opcodes = 40, decoder = RecolorDecoder.class)
    int[] recolorOriginal;
    int[] recolorTarget;

    @Serialize(opcodes = 60, decoder = PascalByteShortArrayDecoder.class)
    int[] additionalModels;

    @Serialize(opcodes = 93, decoder = BoolFalseDecoder.class)
    boolean showOnMinimap = true;

    @Serialize(opcodes = 95, decoder = ShortDecoder.class)
    int combatLevel;

    @Serialize(opcodes = 97, decoder = ShortDecoder.class)
    int scaleXZ;
    @Serialize(opcodes = 98, decoder = ShortDecoder.class)
    int scaleY;
    @Serialize(opcodes = 99, decoder = BoolTrueDecoder.class)
    boolean invisible = false;
    @Serialize(opcodes = 100, decoder = ByteDecoder.class)
    int lightModifier;
    @Serialize(opcodes = 101, decoder = ByteMulFiveDecoder.class)
    int shadowModifier;
    @Serialize(opcodes = 102, decoder = ShortDecoder.class)
    int headIcon;
    @Serialize(opcodes = 103, decoder = ShortDecoder.class)
    int degreesToTurn;

    @Serialize(opcodes = 106, decoder = VarbitDecoder.class)
    int varBitId;
    int sessionSettingId;
    int[] childrenIds;
    @Serialize(opcodes = 107, decoder = BoolFalseDecoder.class)
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
