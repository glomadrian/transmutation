package com.glomadrian.transmutation.repository.mapper;

import com.glomadrian.transmutation.core.Mapper;
import com.glomadrian.transmutation.core.repository.exception.ItemNotFoundException;
import com.glomadrian.transmutation.exceptions.MapperNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Garcia Lomas
 */
public class MapperRepositoryImp implements MapperRepository {

    List<Mapper> mapperList;

    public MapperRepositoryImp() {
        mapperList = new ArrayList<Mapper>();
    }

    @Override
    public Mapper search(Class from, Class to) throws MapperNotFoundException {

        for(Mapper mapper : mapperList){
           if(mapper.getFromClass().equals(from) && mapper.getToClass().equals(to)){
               return mapper;
           }
        }
         throw new MapperNotFoundException();
    }

    @Override
    public void remove(Mapper mapper) {
        mapperList.remove(mapper);
    }

    @Override
    public void add(Mapper item) {
         mapperList.add(item);
    }

    @Override
    public Mapper search(String param) throws ItemNotFoundException {

        for(Mapper mapper : mapperList){
            if(mapper.getFromClass().getName().equals(param)){
                return mapper;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public boolean isEmpty() {
        return mapperList.isEmpty();
    }
}
