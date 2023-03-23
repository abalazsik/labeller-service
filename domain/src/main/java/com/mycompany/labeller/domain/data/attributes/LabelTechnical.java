package com.mycompany.labeller.domain.data.attributes;

import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelTechnical {

    private final boolean value;

    private LabelTechnical(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }

    public static LabelTechnical TRUE = new LabelTechnical(true);
    public static LabelTechnical FALSE = new LabelTechnical(false);

    public static LabelTechnical of(boolean value) {
        return value ? TRUE : FALSE;
    }
}
