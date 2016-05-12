package me.mdbell.jag.util;

import me.mdbell.jag.Constants;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by matthew on 5/7/16.
 */
public class BZ2HeaderInputStream extends FilterInputStream implements Constants {

    private int pos = 0;

    public BZ2HeaderInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public int read() throws IOException {
        if(pos < HEADER_LENGTH) {
            return BZIP_HEADER[pos++];
        }
        return in.read();
    }
}
