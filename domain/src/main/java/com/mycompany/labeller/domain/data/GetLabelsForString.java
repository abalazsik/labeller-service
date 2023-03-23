package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.GetLabelsForStringText;

/**
 *
 * @author ador
 */
public class GetLabelsForString extends DomainObject {

    private final GetLabelsForStringText value;

    public GetLabelsForString(GetLabelsForStringText value) {
        notNull(value);
        this.value = value;
    }

    public GetLabelsForStringText getValue() {
        return value;
    }

}
