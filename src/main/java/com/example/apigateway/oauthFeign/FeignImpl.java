package com.example.apigateway.oauthFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@FeignClient(value = "token" ,url="http://10.20.3.173:8097",fallbackFactory = FallBack.class)
public interface FeignImpl {
    @RequestMapping(method = RequestMethod.GET,value = "/user-service/user/token")
    boolean isTokenValid();
}
