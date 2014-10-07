package com.glomadrian.transmutation.core;

/**
 * @author Adrian Garcia Lomas
 */
public interface Mapper <FromClass, ToClass> {

    <ToClass> ToClass map(FromClass from, Class<ToClass> to);
    Class getToClass();
    Class getFromClass();
    void setTransmutation(MapProcessor mapProcessor);

}
