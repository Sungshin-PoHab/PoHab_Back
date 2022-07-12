package com.example.pohab.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/test")
    public String findCalendar() {
        return "Hello World";
    }

}