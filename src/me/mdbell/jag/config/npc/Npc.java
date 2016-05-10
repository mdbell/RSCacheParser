package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.Serialize;
import me.mdbell.jag.config.decoders.*;

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
    //todo figure out how to do actions
    final String[] actions = new String[10];
    @Serialize(opcodes = 12, decoder = ByteDecoder.class)
    int boundDim;
    @Serialize(opcodes = 13, decoder = ShortDecoder.class)
    int idleAnimation;
    @Serialize(opcodes = 95, decoder = ShortDecoder.class)
    int combatLevel;
    //TODO walkAnim gets set in two opcodes... need to figure that out
    int walkAnimIndex, turn180Index, turn90CWAnimIndex, turn90CCWAnimIndex;
    int[] recolorOriginal, recolorTarget;
    @Serialize(opcodes = 60, decoder = PascalByteShortArrayDecoder.class)
    int[] additionalModels;
    @Serialize(opcodes = 97, decoder = ShortDecoder.class)
    int scaleXZ;
    @Serialize(opcodes = 98, decoder = ShortDecoder.class)
    int scaleY;
    @Serialize(opcodes = 93, decoder = BoolFalseDecoder.class)
    boolean showOnMinimap = true;
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

    //TODO all 3 of these get set in one opcode...
    int varBitId, sessionSettingId;
    int[] childrenIds;
    @Serialize(opcodes = 107, decoder = BoolFalseDecoder.class)
    boolean clickable = true;
}
