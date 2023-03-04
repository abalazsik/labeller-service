package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.exceptions.LabellerException;

/**
 *
 * @author ador
 */
public class DomainObject {

    public void notNull(Object t) {
        if (t == null) {
            throw new LabellerException(this.getClass().getTypeName() + " cannot contain a null value!");
        }
    }

    public void notNull(Object... ts) {
        int param = 0;
        for (Object t : ts) {
            if (t == null) {
                throw new LabellerException(this.getClass().getSimpleName() + " cannot contain a null value for parameter " + (param++));
            }
        }
    }
}
