package com.glomadrian.transmutation.core;

/**
 * @author Adrian Garcia Lomas
 */
public abstract class Rule {

    protected String fromField;
    protected String toField;

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
