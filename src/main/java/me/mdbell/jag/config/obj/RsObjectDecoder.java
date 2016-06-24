package me.mdbell.jag.config.obj;

import me.mdbell.jag.config.IndexedConfigDecoder;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by matthew on 5/11/16.
 */
public class RsObjectDecoder extends IndexedConfigDecoder<RsObject> {

    public RsObjectDecoder(ByteBuffer buffer, int[] indices) {
        super(buffer, indices);
    }

    @Override
    public RsObject forId(int id) throws IOException {
        RsObject res = super.forId(id);
        if (res != null) {
            res.id = id;
        }
        return res;
    }

    @Override
    protected RsObject createTarget() {
        return new RsObject();
    }
}
