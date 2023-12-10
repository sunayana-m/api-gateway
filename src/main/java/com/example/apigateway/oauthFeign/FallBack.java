package com.example.apigateway.oauthFeign;

import com.example.apigateway.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements FallbackFactory<FeignImpl> {

    @Override
    public FeignImpl create(Throwable cause) {
        return new FeignImpl() {
            @Override
            public User isTokenValid(String header) {
                return null;
            }
        };
    }
}
