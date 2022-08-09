package com.example.pohab.Login.Controller;

import com.example.pohab.Entity.User;
import com.example.pohab.Login.JWT.JwtProperties;
import com.example.pohab.Login.Model.OauthToken;
import com.example.pohab.Login.Service.KakaoUserService;
import com.example.pohab.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class UserController {

    private final KakaoUserService kakaoUserService;
    private final UserService userService;

    @GetMapping("/oauth/token") // 프론트에서 인가코드 받아오는 url
    public String getLogin(@RequestParam("code") String code) {
        // 넘어온 인가 코드를 통해 access_token 발급
        OauthToken oauthToken = kakaoUserService.getAccessToken(code);

        // 발급 받은 accessToken 으로 카카오 회원 정보 DB 저장 후 JWT 를 생성
        String jwtToken = kakaoUserService.saveUserAndGetToken(oauthToken.getAccess_token());

        // 응답 헤더의 Authorization 항목에 JWT를 넣음
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        String userEmail = kakaoUserService.getUserEmail(oauthToken.getAccess_token());
        String userName = kakaoUserService.getUserName(userEmail);

        User user = User.builder()
                .email(userEmail)
                .name(userName)
                .build();

        System.out.println("user email: " + userEmail);
        System.out.println("user name: " + userName);

        // User tbl에도 정보를 저장하도록
        userService.save(user);

//        // 사용자 아이디를 헤더의 userId 항목에 담아 넘긴다.
//        headers.add(JwtProperties.USER_EMAIL, userEmail);
//
//        // JWT가 담긴 헤더와 200 ok, "success"라는 body를 ResponseEntity에 담아 프론트에 전달
//        return ResponseEntity.ok().headers(headers).body("success");

        return userEmail;
    }

}