package com.mycompany.labeller.domain.data.attributes;

import com.mycompany.labeller.domain.exceptions.LabellerException;
import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelVersion extends DomainAttribute {
    private final long value;

    public LabelVersion(long value) {
        if (value < 1) {
            throw new LabellerException("version must be greater than 0!");
        }
        this.value = value;
    }

    public long getValue() {
        return value;
    }
    
    public LabelVersion inc() {
        return new LabelVersion(value + 1);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.value ^ (this.value >>> 32));
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
        final LabelVersion other = (LabelVersion) obj;
        return this.value == other.value;
    }
    
    
}
