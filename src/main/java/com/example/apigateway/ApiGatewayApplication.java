package com.example.apigateway;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
        import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
        import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
        import org.springframework.cloud.openfeign.EnableFeignClients;
        import org.springframework.web.bind.annotation.CrossOrigin;


@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
@CrossOrigin
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
