package com.mycompany.labeller.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ador
 */
@Configuration
@ConfigurationProperties("labeller.users")
public class UsersProperties {

    @Value("admin.username")
    public String adminUsername;
    @Value("admin.password")
    public String adminPassword;

    @Value("auditor.username")
    public String auditorUsername;
    @Value("auditor.password")
    public String auditorPassword;
}
