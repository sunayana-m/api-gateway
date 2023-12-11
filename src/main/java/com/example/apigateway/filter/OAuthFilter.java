package com.example.apigateway.filter;


import com.example.apigateway.entity.User;
import com.example.apigateway.oauthFeign.FeignImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@CrossOrigin
public class OAuthFilter extends ZuulFilter {

    @Autowired
    FeignImpl feign;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        String authorizationHeader = context.getRequest().getHeader("authorization");
        String authorizedQueryParam = context.getRequest().getParameter("authorized");
        if (authorizedQueryParam == null || authorizedQueryParam.equals("false")) {
            context.setSendZuulResponse(true);
            context.addZuulRequestHeader("Authorization", authorizationHeader);
        } else if (authorizedQueryParam.equals("true")) {
            boolean isTokenValid = feign.validateCustomAccessToken(authorizationHeader);
            if (isTokenValid) {
                context.setSendZuulResponse(true);
            } else {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                context.setResponseBody("token Expired Login in again");
            }
        }
        return null;
    }
}

