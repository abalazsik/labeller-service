package com.mycompany.labeller.domain.data.attributes;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author ador
 */
public class LabelCreationDate extends DomainAttribute {

    private final LocalDateTime value;

    public LabelCreationDate(LocalDateTime value) {
        notNull(value);
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.value);
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
        final LabelCreationDate other = (LabelCreationDate) obj;
        return Objects.equals(this.value, other.value);
    }
    
    public LocalDateTime getValue() {
        return value;
    }
    
}
