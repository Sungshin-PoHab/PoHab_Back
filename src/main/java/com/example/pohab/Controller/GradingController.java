package com.example.pohab.Controller;

import com.example.pohab.Entity.GradingStandard;
import com.example.pohab.GradingStandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaServer
@SpringBootApplication
@RequestMapping(path="/grading")
public class GradingController {
    @Autowired
    private GradingStandardRepository gradingStandardRepository;

    // repository test code
    @GetMapping("/")
    public @ResponseBody Iterable<GradingStandard> getAllGradingStandard () {
        return gradingStandardRepository.findAll();
    }
}
