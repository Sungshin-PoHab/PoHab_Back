package com.example.pohab.Login.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.pohab.Entity.User;
import com.example.pohab.Login.JWT.JwtProperties;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Login.Service.KakaoUserService;
import com.example.pohab.Login.Service.UserDetailsServiceImpl;
import com.example.pohab.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtHeader = ((HttpServletRequest)request).getHeader(JwtProperties.HEADER_STRING);

        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");

        Long userId = null;
        String userEmail = null;
        String userName = null;

        try {
            userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                    .getClaim("id").asLong();

            userEmail = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                    .getClaim("email").asString();

            userName = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                    .getClaim("name").asString();

            UserDetails userDetails = new UserDetailsImpl(userId, userEmail, userName);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);


        } catch (TokenExpiredException e) {
            e.printStackTrace();
            request.setAttribute(JwtProperties.HEADER_STRING, "토큰이 만료되었습니다.");
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            request.setAttribute(JwtProperties.HEADER_STRING, "유효하지 않은 토큰입니다.");
        }

        request.setAttribute("userId", userId);
        request.setAttribute("userEmail", userEmail);
        request.setAttribute("userName", userName);
        //여기서 에러나는데 대체 왜 나는지 알 수가 없음 하.
        filterChain.doFilter(request, response);
    }
}
