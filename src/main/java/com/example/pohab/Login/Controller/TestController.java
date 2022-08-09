package com.example.pohab.Login.Controller;

import com.example.pohab.Login.JWT.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class TestController {

    // 사용자 ID 받아오기
    @GetMapping("/result")
    public void requestSomething2(@RequestHeader(JwtProperties.HEADER_STRING) String authorization) {
        System.out.println("authorization: " + authorization);
    }

}
