package me.mdbell.jag.images;

import java.io.IOException;
import java.nio.ByteBuffer;

import static me.mdbell.jag.util.Utils.*;

/**
 * Created by matthew on 5/12/16.
 */
public class ImageDecoder {

    public static final int VIRTICAL_ENCODING = 0;
    public static final int HORIZONTAL_ENCODING = 1;

    private ByteBuffer data;
    private ByteBuffer index;

    public ImageDecoder(ByteBuffer data, ByteBuffer index) {
        this.data = data;
        this.index = index;
    }

    public Image decode(int id) throws IOException {
        data.position(0);
        int headerPos = readUShort(data);
        index.position(headerPos);
        Image res = new Image();
        res.maxWidth = readUShort(index);
        res.maxHeight = readUShort(index);
        int palSize = readUByte(index);
        int[] palette = new int[palSize];
        for (int i = 1; i < palSize; i++) {
            int color = readTriInt(index);
            palette[i] = color == 0 ? 1 : color;
        }

        for (int i = 0; i < id; i++) {
            index.get(); // skip renderXOffset
            index.get(); // skip renderYOffset
            //skip pixel data
            data.position(data.position() + readUShort(index) * readUShort(index));
            index.get(); // imageType skip
        }

        res.renderXOffset = readUByte(index);
        res.renderYOffset = readUByte(index);
        res.width = readUShort(index);
        res.height = readUShort(index);
        int type = readUByte(index);
        res.pixels = new int[res.width * res.height];
        switch (type) {
            case VIRTICAL_ENCODING:
                for (int i = 0; i < res.pixels.length; i++) {
                    res.pixels[i] = palette[readUByte(data)];
                }
                break;
            case HORIZONTAL_ENCODING:
                for (int x = 0; x < res.width; x++) {
                    for (int y = 0; y < res.height; y++) {
                        res.pixels[x + y * res.width] = palette[readUByte(data)];
                    }
                }
                break;
            default:
                throw new IOException("Unexpected ImageType!");
        }
        return res;
    }
}
