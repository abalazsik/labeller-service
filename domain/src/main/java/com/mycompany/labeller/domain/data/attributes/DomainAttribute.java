package com.mycompany.labeller.domain.data.attributes;

import com.mycompany.labeller.domain.exceptions.LabellerException;

/**
 *
 * @author ador
 */
public class DomainAttribute {

    public static void notNull(Object t, String msg) {
        if (t == null) {
            throw new LabellerException(msg);
        }
    }
    
    public void notNull(Object t) {
        notNull(t, this.getClass().getTypeName() + " cannot contain a null value!");
    }

}
