package me.mdbell.jag.config;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by matthew on 5/8/16.
 */
public interface OpcodeDecoder<T> {
    void decode(InputStream in, T target) throws IOException;
}
