package com.glomadrian.transmutation.repository.rule;

import com.glomadrian.transmutation.core.repository.Repository;
import com.glomadrian.transmutation.core.repository.exception.ItemNotFoundException;

import java.util.List;

/**
 * @author Adrian Garcia Lomas
 */
public interface RuleRepository<T1> extends Repository<T1> {

    T1 search(String fromField, String toField) throws ItemNotFoundException;
    T1 searchByToField(String toField) throws ItemNotFoundException;
    List<T1> getAsList();

}
