package me.mdbell.jag.config.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by matthew on 5/11/16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OpcodeOffset {
    int value();
}
