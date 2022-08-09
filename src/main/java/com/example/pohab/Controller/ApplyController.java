package com.example.pohab.Controller;

import com.example.pohab.DTO.ApplyUserForPartyDTO;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Service.AnswerService;
import com.example.pohab.Service.ApplyStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    // 동아리 지원 -> apply_status에 저장 및 answer null로 초기화.
    @PostMapping("/saveStatus")
    public ApplyStatus saveStatus(@RequestBody ApplyUserForPartyDTO applyUserForPartyDTO) {
        ApplyStatus applyStatus = this.applyStatusService.applyUserTForParty(applyUserForPartyDTO);
        this.answerService.saveEmptyAnswers(applyStatus, applyStatus.getDepartment().getId());
        return applyStatus;
    }

}
