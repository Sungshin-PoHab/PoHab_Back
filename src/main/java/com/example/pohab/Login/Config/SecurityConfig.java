package com.example.pohab.Login.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // POST 관련
@EnableWebSecurity // 기본적인 Web 보안을 활성화함.
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 추가적인 웹 설정을 위해 상속

    @Autowired
    CorsConfig corsConfig;

    @Override // WebSecurityConfigurerAdapter 내부 configure 함수를 오버라이드
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 해제
                .addFilter(corsConfig.corsFilter()) // corsConfig 등록
                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근 제한 설정
                .antMatchers("/oauth/token", "/**").permitAll() // "/oauth/token"에 대한 접근 권한은 인증 없이 접근 허용 -> CORS 문제 해결
                .anyRequest().permitAll();
    }

}


