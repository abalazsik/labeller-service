package com.mycompany.labeller.domain.data.attributes;

/**
 *
 * @author ador
 */
public class GetLabelsForString extends DomainAttribute {

    public final String value;

    public GetLabelsForString(String value) {
        notNull(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
