package com.glomadrian.transmutation.core;

/**
 * @author Adrian Garcia Lomas
 */
public interface Mapper <fromClass, toClass> {

    <toClass> toClass map(fromClass from, Class<toClass> to);
    Class getToClass();
    Class getFromClass();

}
