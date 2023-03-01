package com.mycompany.labeller.config;

import com.mycompany.labeller.h2.H2Label;
import com.mycompany.labeller.h2.repository.H2LabelRepostitory;
import com.mycompany.labeller.h2.service.H2LabelRowMapper;
import java.util.Arrays;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 *
 * @author ador
 */
@EnableCaching
@Configuration
@ComponentScan(basePackages = {
    "com.mycompany.labeller.h2.repository",
    "com.mycompany.labeller.h2.service"
})
@EnableJdbcRepositories({"com.mycompany.labeller.h2.repository"})
public class H2Config {

    @Bean
    QueryMappingConfiguration rowMappers() {
        return new DefaultQueryMappingConfiguration()
                .registerRowMapper(H2Label.class, new H2LabelRowMapper());
    }

    @Bean
    public CacheManager cacheManager() {

        Cache cache = new ConcurrentMapCache(H2LabelRepostitory.CLASSIFYABLE);

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(cache));

        return manager;
    }
}
