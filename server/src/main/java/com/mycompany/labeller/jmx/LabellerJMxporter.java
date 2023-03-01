package com.mycompany.labeller.jmx;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 *
 * @author ador
 */
@EnableMBeanExport(registration = RegistrationPolicy.REPLACE_EXISTING)
@Configuration
public class LabellerJMxporter {

}
