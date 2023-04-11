package com.mycompany.labeller.domain.data.attributes;

import java.time.LocalDateTime;
import java.util.Objects;
import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelUpdateDate extends NullableLocalDateTimeAttribute {

    public LabelUpdateDate(LocalDateTime value) {
        super(value);
    }

}
