package com.mycompany.labeller.domain.data.attributes;

/**
 *
 * @author ador
 */
public class NullableStringAttribute extends DomainAttribute {

    private final String value;

    public NullableStringAttribute(String value) {
        this.value = value;
    }

    public static String getValue(NullableStringAttribute nnsa) {
        if (nnsa == null) {
            return null;
        }
        return nnsa.value;
    }
}
