package com.mycompany.labeller.domain.data.attributes;

import com.mycompany.labeller.domain.exceptions.LabellerException;
import org.jmolecules.ddd.annotation.ValueObject;

/**
 *
 * @author ador
 */
@ValueObject
public class LabelClassifierData extends NullableStringAttribute {

    public LabelClassifierData(String value) {
        super(value);
        if (value != null) {
            checkFormat(value); // should be before the super call, but you can't do that in JAVA 
        }
    }

    private static void checkFormat(String text) {
        int count = 0;

        for (char ch : text.toCharArray()) {
            if (ch == '"') {
                count++;
            }
        }

        if (count % 2 == 1) {
            throw new LabellerException(String.format("Invalid classifierdata format: %s", text));
        }
    }

}
