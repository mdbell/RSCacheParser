package me.mdbell.jag.config;

/**
 * Created by matthew on 5/9/16.
 */
public class DecodeContext<K, T> {

    private int opcode;
    private int offset;
    private K target;
    private T value;

    public DecodeContext(int opcode, K target) {
        this.opcode = opcode;
        this.target = target;
    }

    public int getOffset() {
        return offset;
    }

    void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOpcode() {
        return opcode;
    }

    public K getTarget() {
        return target;
    }

    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }
}
