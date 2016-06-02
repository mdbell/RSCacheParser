package me.mdbell.jag.config.obj;

import me.mdbell.jag.config.annotations.ArrayOffset;
import me.mdbell.jag.config.annotations.RangeSerialize;
import me.mdbell.jag.config.annotations.Serialize;
import me.mdbell.jag.config.codecs.*;

import java.util.Arrays;

/**
 * Created by matthew on 5/11/16.
 */
public class RsObject {

    int id;

    @Serialize(opcodes = 1, codec = ObjectModelCodec.class)
    int[] modelIndices;
    int[] modelTypes;

    @Serialize(opcodes = 2, codec = StringCodec.class)
    String name;
    @Serialize(opcodes = 3, codec = StringCodec.class)
    String desc;
    @Serialize(opcodes = 5, codec = PascalByteShortArrayCodec.class)
    int[] models;
    @Serialize(opcodes = 14, codec = ByteCodec.class)
    int sizeX;
    @Serialize(opcodes = 15, codec = ByteCodec.class)
    int sizeY;
    @Serialize(opcodes = 17, codec = BoolFalseCodec.class)
    boolean hasCollisions = true;
    @Serialize(opcodes = 18, codec = BoolFalseCodec.class)
    boolean blocksProjectiles = true;
    @Serialize(opcodes = 19, codec = BooleanCodec.class)
    boolean isStatic = false;
    @Serialize(opcodes = 21, codec = BoolTrueCodec.class)
    boolean fitsToTerrain = false;
    @Serialize(opcodes = 22, codec = BoolTrueCodec.class)
    boolean flatShading = false;
    @Serialize(opcodes = 23, codec = BoolTrueCodec.class)
    boolean isSolid = false;
    @Serialize(opcodes = 24, codec = ShortCodec.class)
    int seqIndex;
    @Serialize(opcodes = 28, codec = ByteCodec.class)
    int wallWidth;
    @Serialize(opcodes = 29, codec = ByteCodec.class)
    int brightness;
    @ArrayOffset(-30)
    @RangeSerialize(min = 30, max = 38, codec = OffsetStringCodec.class)
    String[] actions = new String[10];
    @Serialize(opcodes = 39, codec = ByteCodec.class)
    int specular;
    @Serialize(opcodes = 40, codec = ObjectColorCodec.class)
    int[] oldColor;
    int[] newColor;
    @Serialize(opcodes = 60, codec = ShortCodec.class)
    int icon;
    @Serialize(opcodes = 62, codec = BoolTrueCodec.class)
    boolean rotateCcw = false;
    @Serialize(opcodes = 64, codec = BoolFalseCodec.class)
    boolean castsShadow = true;
    @Serialize(opcodes = 65, codec = ShortCodec.class)
    int scaleX;
    @Serialize(opcodes = 66, codec = ShortCodec.class)
    int scaleY;
    @Serialize(opcodes = 67, codec = ShortCodec.class)
    int scaleZ;
    @Serialize(opcodes = 68, codec = ShortCodec.class)
    int sceneImageIndex;
    @Serialize(opcodes = 69, codec = ByteCodec.class)
    int faceFlags;
    @Serialize(opcodes = 70, codec = ShortCodec.class)
    int offsetX;
    @Serialize(opcodes = 71, codec = ShortCodec.class)
    int offsetY;
    @Serialize(opcodes = 72, codec = ShortCodec.class)
    int offsetZ;
    @Serialize(opcodes = 73, codec = BoolTrueCodec.class)
    boolean isDecoration = false;
    @Serialize(opcodes = 74, codec = BoolTrueCodec.class)
    boolean ghost = false;
    @Serialize(opcodes = 75, codec = ByteCodec.class)
    int raisesItemPiles;
    @Serialize(opcodes = 77, codec = ObjectVarbitCodec.class)
    int varBitId = -1;
    int sessionSettingId = -1;
    int[] childrenIds;

    @Override
    public String toString() {
        return "RsObject{" +
                "id=" + id +
                ", modelIndices=" + Arrays.toString(modelIndices) +
                ", modelTypes=" + Arrays.toString(modelTypes) +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", models=" + Arrays.toString(models) +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", hasCollisions=" + hasCollisions +
                ", blocksProjectiles=" + blocksProjectiles +
                ", isStatic=" + isStatic +
                ", fitsToTerrain=" + fitsToTerrain +
                ", flatShading=" + flatShading +
                ", isSolid=" + isSolid +
                ", seqIndex=" + seqIndex +
                ", wallWidth=" + wallWidth +
                ", brightness=" + brightness +
                ", actions=" + Arrays.toString(actions) +
                ", specular=" + specular +
                ", oldColor=" + Arrays.toString(oldColor) +
                ", newColor=" + Arrays.toString(newColor) +
                ", icon=" + icon +
                ", rotateCcw=" + rotateCcw +
                ", castsShadow=" + castsShadow +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                ", scaleZ=" + scaleZ +
                ", sceneImageIndex=" + sceneImageIndex +
                ", faceFlags=" + faceFlags +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", offsetZ=" + offsetZ +
                ", isDecoration=" + isDecoration +
                ", ghost=" + ghost +
                ", raisesItemPiles=" + raisesItemPiles +
                ", varBitId=" + varBitId +
                ", sessionSettingId=" + sessionSettingId +
                ", childrenIds=" + Arrays.toString(childrenIds) +
                '}';
    }
}
