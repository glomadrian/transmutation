package com.glomadrian.transmutation.core;

import com.glomadrian.transmutation.exceptions.MapperNotFoundException;

/**
 * @author Adrian Garcia Lomas
 */
public interface MapProcessor {

    void addTransmutator(Mapper mapper);
    void removeTransmutator(Mapper mapper);
    public <T> T transmute(Object from, Class<T> to) throws MapperNotFoundException;
}
