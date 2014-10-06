package com.glomadrian.transmutation.core.repository;

import com.glomadrian.transmutation.core.repository.exception.ItemNotFoundException;

/**
 * @author Adrian Garcia Lomas
 */
public interface Repository<T1> {

    void add(T1 item);
    T1 search(String param) throws ItemNotFoundException;
    boolean isEmpty();
}
