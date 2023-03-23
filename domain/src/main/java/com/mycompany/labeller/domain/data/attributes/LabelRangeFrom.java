
package com.mycompany.labeller.domain.data.attributes;

import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelRangeFrom extends DomainAttribute {
    private final int value;

    public LabelRangeFrom(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("from must be greater than or equal to 0");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
