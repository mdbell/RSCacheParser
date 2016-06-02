package me.mdbell.jag.config;

/**
 * Created by matthew on 5/11/16.
 */
public class EncodeContext<T, K> {

    private T target;
    private K value;

    public K getValue() {
        return value;
    }

    protected void setValue(K value) {
        this.value = value;
    }

    public T getTarget() {
        return target;
    }

    protected void setTarget(T target) {
        this.target = target;
    }
}
