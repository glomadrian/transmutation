package com.glomadrian.transmutation.rule;

import com.glomadrian.transmutation.core.Rule;

/**
 * @author Adrian Garcia Lomas
 */
public abstract class ComplexFieldRule<FieldFrom,FieldTo> extends Rule {

    protected ComplexFieldRule(String fromField, String toField) {
        this.fromField = fromField;
        this.toField = toField;
    }

    public abstract FieldTo map(FieldFrom fieldFrom);
}
