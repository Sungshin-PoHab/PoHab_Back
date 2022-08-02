package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateStepDto;
import com.example.pohab.Entity.Step;
import com.example.pohab.Service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableEurekaServer
@SpringBootApplication
@RequestMapping(path = "/recruit")
public class RecruitmentController {
    private RecruitService recruitService;

    @Autowired
    public RecruitmentController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    @PostMapping("/{party_id}")
    public List<Step> createStep(@PathVariable("party_id") String party_id, @RequestBody ArrayList<CreateStepDto> createStepDto) {
        return this.recruitService.createStep(party_id, createStepDto);
    }

    @GetMapping("/step")
    public List<Step> getAllStep() {
        return this.recruitService.getAllStep();
    }
}
