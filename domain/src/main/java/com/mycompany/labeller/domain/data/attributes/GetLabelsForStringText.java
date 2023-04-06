package com.mycompany.labeller.domain.data.attributes;

import com.mycompany.labeller.domain.exceptions.LabellerException;
import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class GetLabelsForStringText extends DomainAttribute {

    private final String value;

    public GetLabelsForStringText(String value) {
        notNull(value);
        if (value.isEmpty()) {
            throw new LabellerException("GetLabelsForStringText cannot be empty!");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
