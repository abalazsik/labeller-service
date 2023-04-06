
package com.mycompany.labeller.domain.data.attributes;

import com.mycompany.labeller.domain.exceptions.LabellerException;
import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelRangeLimit extends DomainAttribute {
    private final int value;

    public LabelRangeLimit(int value) {
        if (value < 1) {
            throw new LabellerException("limit must be greater than 0");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
