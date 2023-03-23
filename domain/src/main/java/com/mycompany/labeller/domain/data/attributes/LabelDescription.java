package com.mycompany.labeller.domain.data.attributes;

import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelDescription extends NullableStringAttribute {

    public LabelDescription(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "LabelDescription{" + "value=" + getValue(this) + '}';
    }

}
