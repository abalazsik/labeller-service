package com.mycompany.labeller.domain.data.attributes;

import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class NullableLocalDateTimeAttribute extends DomainAttribute {

    private final LocalDateTime value;

    public NullableLocalDateTimeAttribute(LocalDateTime value) {
        this.value = value;
    }

    public static LocalDateTime getValue(NullableLocalDateTimeAttribute nnsa) {
        if (nnsa == null) {
            return null;
        }
        return nnsa.value;
    }

}
