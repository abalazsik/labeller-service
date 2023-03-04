
package com.mycompany.labeller.web.data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author ador
 */
public class WebGetLabelsForString {
    
    @NotNull
    public String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
