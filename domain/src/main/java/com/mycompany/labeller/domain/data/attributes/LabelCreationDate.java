package com.mycompany.labeller.domain.data.attributes;

import java.time.LocalDateTime;
import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelCreationDate extends NullableLocalDateTimeAttribute {

    public LabelCreationDate(LocalDateTime value) {
        super(value);
    }

}
