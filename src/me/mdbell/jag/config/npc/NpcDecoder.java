package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.ConfigDecoder;

import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class NpcDecoder extends ConfigDecoder<Npc>{
    public NpcDecoder(ByteBuffer buffer) {
        super(buffer);
    }

    @Override
    protected Npc createTarget() {
        return new Npc();
    }
}
