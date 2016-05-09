package me.mdbell.jag.config.npc;

import java.util.Arrays;

/**
 * Created by matthew on 5/8/16.
 */
public class Npc {

    int[] models;
    String name, desc;
    final String[] actions = new String[10];
    int boundDim;
    int combatLevel;
    int idleAnimation;
    int walkAnimIndex, turn180Index, turn90CWAnimIndex, turn90CCWAnimIndex;
    int[] recolorOriginal, recolorTarget;
    int[] additionalModels;
    int scaleY;

    @Override
    public String toString() {
        return "Npc{" +
                "models=" + Arrays.toString(models) +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", actions=" + Arrays.toString(actions) +
                ", boundDim=" + boundDim +
                ", combatLevel=" + combatLevel +
                ", idleAnimation=" + idleAnimation +
                ", walkAnimIndex=" + walkAnimIndex +
                ", turn180Index=" + turn180Index +
                ", turn90CWAnimIndex=" + turn90CWAnimIndex +
                ", turn90CCWAnimIndex=" + turn90CCWAnimIndex +
                '}';
    }
}
