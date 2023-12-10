package com.example.apigateway.config;


import com.example.apigateway.filter.OAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ZuulConfig {
    @Bean
    public OAuthFilter queryParamFilter() {
        return new OAuthFilter();
    }
}
