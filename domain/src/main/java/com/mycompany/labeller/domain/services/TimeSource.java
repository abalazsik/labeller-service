package com.mycompany.labeller.domain.services;

import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public interface TimeSource {

    public LocalDateTime now();
}
