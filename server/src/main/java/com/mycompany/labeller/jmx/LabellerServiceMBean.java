package com.mycompany.labeller.jmx;

import com.mycompany.labeller.domain.data.Label;
import java.util.Optional;

/**
 *
 * @author ador
 */
public interface LabellerServiceMBean {

    public Optional<Label> getById(long id);
}
