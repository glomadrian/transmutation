package com.glomadrian.transmutation;

import com.glomadrian.transmutation.core.MapProcessor;
import com.glomadrian.transmutation.core.Mapper;
import com.glomadrian.transmutation.exceptions.MapperNotFoundException;
import com.glomadrian.transmutation.repository.mapper.MapperRepository;
import com.glomadrian.transmutation.repository.mapper.MapperRepositoryImp;

/**
 * @author Adrian Garcia Lomas
 */
public class Transmutation implements MapProcessor {

    private MapperRepository mapperRepository;

    public Transmutation() {
        mapperRepository = new MapperRepositoryImp();
    }

    @Override
    public void addTransmutator(Mapper mapper) {
        mapper.setTransmutation(this);
        mapperRepository.add(mapper);
    }

    @Override
    public void removeTransmutator(Mapper mapper) {
        mapperRepository.remove(mapper);
    }

    @Override
    public <T> T transmute(Object from, Class<T> toClass) throws MapperNotFoundException {

        Class fromClass = from.getClass();
        Mapper mapper = mapperRepository.search(fromClass,toClass);

        return (T) mapper.map(from,toClass);
    }


}
