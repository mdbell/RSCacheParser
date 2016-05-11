package me.mdbell.jag.util;

import me.mdbell.jag.cache.CacheFile;
import me.mdbell.jag.cache.Constants;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author matt123337
 */
public class Utils implements Constants {

    public static int readTriInt(InputStream in) throws IOException {
        return readUByte(in) << 16 | readUShort(in);
    }

    public static int readUByte(InputStream in) throws IOException {
        return in.read() & 0xFF;
    }

    public static int readUShort(InputStream in) throws IOException {
        return (readUByte(in) << 8) | readUByte(in);
    }

    public static int readInt(InputStream in) throws IOException {
        return (readUShort(in) << 16) | readUShort(in);
    }

    public static int readTriInt(ByteBuffer buffer) {
        return (readUByte(buffer) << 16) | readUShort(buffer);
    }

    public static int readUShort(ByteBuffer buffer) {
        return buffer.getShort() & 0xFFFF;
    }

    public static int readUByte(ByteBuffer buffer) {
        return buffer.get() & 0xFF;
    }

    public static int readInt(ByteBuffer buffer) {
        return readUShort(buffer) << 16 | readUShort(buffer);
    }

    public static String readString(ByteBuffer source) {
        return readString(source, '\n');
    }

    public static String readString(ByteBuffer buffer, int delim) {
        int pos = buffer.position();
        int i;
        while (buffer.remaining() > 0 && (i = readUByte(buffer)) != delim) ;
        return new String(buffer.array(), pos, buffer.position() - pos - 1);
    }

    public static String readString(InputStream in) throws IOException {
        return readString(in, '\n');
    }

    public static String readString(InputStream in, int delim) throws IOException {
        return readString(in, 1024 * 8, delim);
    }

    public static String readString(InputStream in, int maxlen, int delim) throws IOException {
        byte[] b = new byte[maxlen];
        int i;
        int read;
        for (read = 0; read < maxlen && (i = in.read()) != -1 && i != delim; read++) {
            b[read] = (byte) i;
        }
        return new String(b, 0, read);
    }

    public static CacheFile[] readFiles(FileChannel channel) throws IOException {
        int size = (int) (channel.size() / INDEX_TOTAL_SIZE);
        ByteBuffer buffer = ByteBuffer.allocate(INDEX_TOTAL_SIZE);
        CacheFile[] res = new CacheFile[size];
        for (int i = 0; i < size; i++) {
            channel.read(buffer);
            buffer.flip(); // reading
            int fileSize = readTriInt(buffer);
            int sector = readTriInt(buffer);
            res[i] = new CacheFile(fileSize, sector);
            buffer.flip();
        }
        return res;
    }

    public static void readFully(FileChannel file, long pos, ByteBuffer buf) throws IOException {
        int n = buf.remaining();
        int toRead = n;
        while (n > 0) {
            int amt = file.read(buf, pos + buf.remaining() - n);
            if (amt == -1) {
                int read = toRead - n;
                throw new EOFException("reached end of stream after reading "
                        + read + " bytes; " + toRead + " bytes expected");
            } else {
                n -= amt;
            }
        }
    }

    public static int getArchiveHash(String s) {
        int res = 0;
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++)
            res = (res * 61 + s.charAt(i)) - 32;
        return res;
    }
}
