package com.mycompany.labeller.domain.services;

import java.time.LocalDateTime;
import org.jmolecules.ddd.annotation.Service;

/**
 *
 * @author ador
 */
@Service
public interface TimeSource {

    public LocalDateTime now();
}
