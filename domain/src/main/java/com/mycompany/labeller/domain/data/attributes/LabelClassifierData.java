package com.mycompany.labeller.domain.data.attributes;

import com.mycompany.labeller.domain.exceptions.LabellerException;

/**
 *
 * @author ador
 */
public class LabelClassifierData {

    private final String value;

    public LabelClassifierData(String value) {
        if (value != null) {
            checkFormat(value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
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
