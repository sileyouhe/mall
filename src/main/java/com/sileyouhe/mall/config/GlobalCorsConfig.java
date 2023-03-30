package com.sileyouhe.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * global cross-origin configuration
 */

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // allow all
        config.addAllowedOriginPattern("*");

        //deprecated in SpringBoot 2.7.0
        //config.addAllowedOrigin("*");

        //  cookie
        config.setAllowCredentials(true);
        //  header
        config.addAllowedHeader("*");
        // method
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
