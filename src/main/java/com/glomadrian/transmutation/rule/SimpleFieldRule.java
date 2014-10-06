package com.glomadrian.transmutation.rule;

import com.glomadrian.transmutation.core.Rule;

/**
 * @author Adrian Garcia Lomas
 */
public class SimpleFieldRule extends Rule{

    public SimpleFieldRule(String fromField, String toField) {
        this.fromField = fromField;
        this.toField = toField;
    }

    public String getFromField() {
        return fromField;
    }

    public void setFromField(String fromField) {
        this.fromField = fromField;
    }

    public String getToField() {
        return toField;
    }

    public void setToField(String toField) {
        this.toField = toField;
    }
}
