package me.mdbell.jag.config;

/**
 * Created by matthew on 5/9/16.
 */
public class DecodeContext<T> {

    private int opcode;
    private int offset;
    private T value;

    public DecodeContext(int opcode, T value) {
        this.opcode = opcode;
        this.value = value;
    }

    public DecodeContext(int opcode, T value, int offset) {
        this(opcode,value);
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public int getOpcode() {
        return opcode;
    }

    public T getValue() {
        return value;
    }
}
