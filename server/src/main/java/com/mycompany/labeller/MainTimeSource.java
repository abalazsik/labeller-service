
package com.mycompany.labeller;

import com.mycompany.labeller.domain.services.TimeSource;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author ador
 */
@Component
public class MainTimeSource implements TimeSource {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
    
}
