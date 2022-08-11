package com.example.pohab.Login.Config;

import com.example.pohab.Login.Filter.CustomAuthenticationEntryPoint;
import com.example.pohab.Login.Filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
//                .antMatchers("/oauth/token", "/**").permitAll() // "/oauth/token"에 대한 접근 권한은 인증 없이 접근 허용 -> CORS 문제 해결
//                .anyRequest().permitAll();

                .antMatchers("/login", "/oauth/token", "/main/**").permitAll() //이 경로는 인증절차 없이 허용
                .antMatchers("/party/enroll").authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

//        http.csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .httpBasic().disable()
//                .formLogin().disable()
//                .addFilter(corsConfig.corsFilter());
//
//        http.authorizeRequests()
//                .antMatchers("/party/enroll")
//                .authenticated()
//                .anyRequest().permitAll()
//
//                .and()
//                //(1)
//                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        http.addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}


