
package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.services.TimeSource;
import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class ConstantTimeSource implements TimeSource {

    private final LocalDateTime value;

    public ConstantTimeSource(LocalDateTime value) {
        this.value = value;
    }
    
    @Override
    public LocalDateTime now() {
        return value;
    }
    
}
