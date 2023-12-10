package com.example.apigateway.oauthFeign;

import com.example.apigateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@FeignClient(value = "token", url = "http://10.20.3.164:9090", fallbackFactory = FallBack.class)
public interface FeignImpl {
    @RequestMapping(method = RequestMethod.GET, value = "private/user-details")
    User isTokenValid(@RequestHeader("Authorization") String authorizationHeader);
}
