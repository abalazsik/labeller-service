package com.mycompany.labeller.domain.data.attributes;

/**
 *
 * @author ador
 */
public class LabelDescription {

    private final String value;

    public LabelDescription(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LabelDescription{" + "value=" + value + '}';
    }
    
}
