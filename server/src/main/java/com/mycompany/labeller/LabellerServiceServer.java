package com.mycompany.labeller;

import com.mycompany.labeller.domain.services.LabelService;
import com.mycompany.labeller.h2.service.H2LabelStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author ador
 */
@SpringBootApplication
@ComponentScan({
    "com.mycompany.labeller.web",
    "com.mycompany.labeller.config",
    "com.mycompany.labeller.security",
    "com.mycompany.labeller.jmx",
    "com.mycompany.labeller"
})
public class LabellerServiceServer {

    @Autowired
    private H2LabelStorage labelStorage;    
    
    /*
    @Autowired
    private Neo4JLabelStorage labelStorage;
    */
    
    @Autowired
    private MainTimeSource mainTimeSource;
    
    public static void main(String[] args) {
        SpringApplication.run(LabellerServiceServer.class, args);
    }

    @Bean
    public LabelService labelService() {
        return new LabelService(labelStorage, mainTimeSource);
    }
}
