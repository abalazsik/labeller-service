package com.mycompany.labeller.domain.data.attributes;

/**
 *
 * @author ador
 */
public class LabelId {

    private final long value;

    public LabelId(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.value ^ (this.value >>> 32));
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
        final LabelId other = (LabelId) obj;
        return this.value == other.value;
    }

}
