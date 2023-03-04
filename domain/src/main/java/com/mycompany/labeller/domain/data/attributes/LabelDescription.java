package com.mycompany.labeller.domain.data.attributes;

/**
 *
 * @author ador
 */
public class LabelDescription extends NullableStringAttribute {
    
    public LabelDescription(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "LabelDescription{" + "value=" + getValue(this) + '}';
    }
    
}
