package com.example.apigateway.oauthFeign;

import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements FallbackFactory<FeignImpl> {

    @Override
    public FeignImpl create(Throwable cause) {
        return new FeignImpl() {
            @Override
            public boolean isTokenValid() {
                return false;
            }
        };
    }
}
