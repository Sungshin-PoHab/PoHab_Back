package com.example.pohab.Controller;

import com.example.pohab.DTO.ApplyUserForPartyDTO;
import com.example.pohab.DTO.CreateStepDto;
import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Question;
import com.example.pohab.Entity.Step;
import com.example.pohab.Service.AnswerService;
import com.example.pohab.Service.ApplyStatusService;
import com.example.pohab.Service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apply")
public class ApplyController {

    private ApplyStatusService applyStatusService;
    private AnswerService answerService;

    @Autowired
    public ApplyController(ApplyStatusService applyStatusService, AnswerService answerService) {
        this.applyStatusService = applyStatusService;
        this.answerService = answerService;
    }

    // 모집 일정(step) 생성
    @PostMapping("/saveStatus")
    public ApplyStatus saveStatus(@RequestBody ApplyUserForPartyDTO applyUserForPartyDTO) {
        return this.applyStatusService.applyUserTForarty(applyUserForPartyDTO);
    }

}
