package me.mdbell.jag.config.npc;

import me.mdbell.jag.config.IndexedConfigDecoder;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/9/16.
 */
public class NpcDecoder extends IndexedConfigDecoder<Npc> {

    public NpcDecoder(ByteBuffer buffer, int[] indices) {
        super(buffer, indices);
    }

    public Npc forId(int id) throws IOException {
        Npc n = super.forId(id);
        n.id = id;
        return n;
    }

    @Override
    protected Npc createTarget() {
        return new Npc();
    }
}
