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
import org.springframework.web.bind.annotation.CrossOrigin;

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
        String requestURI = context.getRequest().getRequestURI();

//        String authorizedQueryParam = context.getRequest().getParameter("authorized");
//        if (authorizedQueryParam == null || authorizedQueryParam.equals("false")) {
//            context.setSendZuulResponse(true);
//        } else if (authorizedQueryParam.equals("true")) {
        String authorizationHeader = context.getRequest().getHeader("authorization");
        User user = feign.isTokenValid(authorizationHeader);
        System.out.println("Theeeeeeee      " + user);
        if (user != null) {
            context.setSendZuulResponse(true);
            if ("/login".equals(requestURI)) {
                context.setSendZuulResponse(false);
                context.getResponse().setHeader("Content-Type", "application/json");
                context.getResponse().setCharacterEncoding("UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String userJson = objectMapper.writeValueAsString(user);
                    context.setResponseBody(userJson);
                } catch (JsonProcessingException e) {
                    // Handle exception if JSON serialization fails
                    e.printStackTrace();
                }
            }
        } else {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("token Expired Login in again");
        }
        return null;

    }
}

