package com.mycompany.labeller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 *
 * @author ador
 */
@Configuration
@ComponentScan(basePackages = {
    "com.mycompany.labeller.neo4j.repository",
    "com.mycompany.labeller.neo4j.service"
})
@EnableNeo4jRepositories(basePackages = {"com.mycompany.labeller.neo4j.repository"})
public class Neo4JConfig {

    @Value("${spring.liquibase.url}")
    private String url;

    @Value("${spring.liquibase.user}")
    private String user;

    @Value("${spring.liquibase.password}")
    private String password;

}
