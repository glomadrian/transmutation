package com.glomadrian.transmutation.repository.mapper;

import com.glomadrian.transmutation.core.Mapper;
import com.glomadrian.transmutation.core.repository.Repository;
import com.glomadrian.transmutation.exceptions.MapperNotFoundException;

/**
 * @author Adrian Garcia Lomas
 */
public interface MapperRepository extends Repository<Mapper> {

    public Mapper search(Class from, Class to) throws MapperNotFoundException;
    public void remove(Mapper mapper);
}
