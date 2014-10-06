package com.glomadrian.transmutation.rule;

import com.glomadrian.transmutation.core.Rule;

/**
 * @author Adrian Garcia Lomas
 */
public abstract class GenerateFieldRule<fromClass> extends Rule{

    public GenerateFieldRule(String toField) {
        this.toField = toField;
    }

    public abstract Object map(fromClass fromClass);


}
