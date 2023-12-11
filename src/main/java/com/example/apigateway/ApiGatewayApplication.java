package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
@CrossOrigin
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

//    @Configuration
//    public class CorsConfig {
//
//        @Bean
//        public CorsFilter corsFilter() {
//            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            final CorsConfiguration config = new CorsConfiguration();
//            config.setAllowCredentials(true);
//            config.addAllowedOrigin("*"); // Allow requests from any origin (you may restrict this to specific origins)
//            config.addAllowedHeader("*"); // Allow all headers
//            config.addAllowedMethod("OPTIONS");
//            config.addAllowedMethod("HEAD");
//            config.addAllowedMethod("GET");
//            config.addAllowedMethod("PUT");
//            config.addAllowedMethod("POST");
//            config.addAllowedMethod("DELETE");
//            config.addAllowedMethod("PATCH");
//            source.registerCorsConfiguration("/**", config);
//            return new CorsFilter(source);
//        }
//    }
}
