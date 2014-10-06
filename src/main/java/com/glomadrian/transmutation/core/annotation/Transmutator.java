package com.glomadrian.transmutation.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Adrian Garcia Lomas
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Transmutator {

    Class fromClass();
    Class toClass();

}
