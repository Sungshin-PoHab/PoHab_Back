package com.example.pohab.Login.Controller;

import com.example.pohab.Entity.User;
import com.example.pohab.Login.JWT.JwtProperties;
import com.example.pohab.Login.Model.OauthToken;
import com.example.pohab.Login.Service.KakaoUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
//@AllArgsConstructor
public class UserController {

    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(KakaoUserService kakaoUserService) {
        this.kakaoUserService = kakaoUserService;
    }

    @GetMapping("/oauth/token") // 프론트에서 인가코드 받아오는 url
    public ResponseEntity<String> getLogin(@RequestParam("code") String code) {
        // 넘어온 인가 코드를 통해 access_token 발급
        OauthToken oauthToken = kakaoUserService.getAccessToken(code);

        // 발급 받은 accessToken 으로 카카오 회원 정보 DB 저장 후 JWT 를 생성
        String jwtToken = kakaoUserService.saveUserAndGetToken(oauthToken.getAccess_token());

        // 응답 헤더의 Authorization 항목에 JWT를 넣음
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken );

        // JWT가 담긴 헤더와 200 ok, "success"라는 body를 ResponseEntity에 담아 프론트에 전달
        return ResponseEntity.ok().headers(headers).body("success");

    }


    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) {

        User user = kakaoUserService.getUser(request);

        return ResponseEntity.ok().body(user);
    }
}