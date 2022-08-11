package com.example.pohab.Login.Filter;

import com.example.pohab.Login.JWT.JwtProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException, IOException {

        System.out.println(request.getHeader("Authorization")); //정상 출력
        System.out.println(authException.getMessage()); //Full authentication is required to access this resource 출력
        String exception = (String) request.getAttribute(JwtProperties.HEADER_STRING);
        String errorCode;

        if (exception != null) {
            if (exception.equals("토큰이 만료되었습니다.")) {
                errorCode = "토큰이 만료되었습니다.";
                setResponse(response, errorCode);
            }

            if (exception.equals("유효하지 않은 토큰입니다.")) {
                errorCode = "유효하지 않은 토큰입니다.";
                setResponse(response, errorCode);
            }
        }
    }

    private void setResponse(HttpServletResponse response, String errorCode) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JwtProperties.HEADER_STRING + " : " + errorCode);
    }
}
