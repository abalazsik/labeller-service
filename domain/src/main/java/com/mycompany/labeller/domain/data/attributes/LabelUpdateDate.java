package com.mycompany.labeller.domain.data.attributes;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author ador
 */
public class LabelUpdateDate extends NullableLocalDateTimeAttribute {

    public LabelUpdateDate(LocalDateTime value) {
        super(value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(getValue(this));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LabelUpdateDate other = (LabelUpdateDate) obj;
        return Objects.equals(getValue(this), getValue(other));
    }

}
