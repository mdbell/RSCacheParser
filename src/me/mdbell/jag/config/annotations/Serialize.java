package me.mdbell.jag.config.annotations;

import me.mdbell.jag.config.Decoder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by matthew on 5/9/16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Serialize {
    int[] opcodes();
    Class<? extends Decoder> decoder();
}
