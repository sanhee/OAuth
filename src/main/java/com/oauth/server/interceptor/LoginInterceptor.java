package com.oauth.server.interceptor;

import com.oauth.server.annotation.LoginRequired;
import com.oauth.server.dto.auth.UserDto;
import com.oauth.server.exception.AuthenticationException;
import com.oauth.server.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isLoginRequired(handler)){
            authenticate(request);
        }
        return true;
    }

    private boolean isLoginRequired(Object handler){
        return handler instanceof HandlerMethod
                && ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class);
    }

    private void authenticate(HttpServletRequest request){
        String authorization = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .orElseThrow(()-> new AuthenticationException("인증되지 않은 유저입니다."));
        String[] splitAuth = authorization.split(" ");
        String tokenType = splitAuth[0].toLowerCase();

        if(splitAuth.length<1 || !tokenType.equals("bearer")){
            throw new AuthenticationException("잘못된 Authorization 타입입니다.");
        }

        UserDto userDto = JwtUtil.decodeJwt(splitAuth[1]);
        request.setAttribute("user", userDto);
    }
}
