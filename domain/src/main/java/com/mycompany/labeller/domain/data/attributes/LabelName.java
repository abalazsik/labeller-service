
package com.mycompany.labeller.domain.data.attributes;

import java.util.Objects;

/**
 *
 * @author ador
 */
public class LabelName extends DomainAttribute {

    private final String value;

    public LabelName(String value) {
        notNull(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LabelName{" + "value=" + value + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.value);
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
        final LabelName other = (LabelName) obj;
        return Objects.equals(this.value, other.value);
    }
    
}
