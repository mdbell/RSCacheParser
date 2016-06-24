package me.mdbell.jag.images;

import java.awt.image.BufferedImage;

/**
 * Created by matthew on 5/12/16.
 */
public class Image {
    public int[] pixels;
    int maxWidth, maxHeight;
    int renderXOffset, renderYOffset;
    int width, height;

    public BufferedImage toBufferedImage() {
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        res.setRGB(0, 0, width, height, pixels, 0, width);
        return res;
    }

    @Override
    public String toString() {
        return "Image{" +
                "maxWidth=" + maxWidth +
                ", maxHeight=" + maxHeight +
                ", renderXOffset=" + renderXOffset +
                ", renderYOffset=" + renderYOffset +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
