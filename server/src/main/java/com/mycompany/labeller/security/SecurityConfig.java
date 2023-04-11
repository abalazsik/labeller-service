package com.mycompany.labeller.security;

import com.mycompany.labeller.commons.security.LabellerUser;
import com.mycompany.labeller.commons.roles.Roles;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author ador
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetailsService(UsersProperties props) {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(
                new LabellerUser(props.adminUsername, props.adminPassword, Roles.Admin),
                new LabellerUser(props.auditorUsername, props.auditorPassword, Roles.Auditor)
        );

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CorsFilter(corsConfigurationSource()), ChannelProcessingFilter.class);
        http
                .authorizeRequests()
                .antMatchers(
                        "/api/labels/forString",
                        "/api/labels/all",
                        "/swagger-ui/*",
                        "/v3/api-docs/swagger-config",
                        "/v3/api-docs",
                        "/h2/**")
                .permitAll()
                .and().authorizeRequests().anyRequest().authenticated().and()
                .httpBasic();
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2/**"));
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    public void configure(AuthenticationManagerBuilder auth, UsersProperties props) throws Exception {
        auth.userDetailsService(getUserDetailsService(props));
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
