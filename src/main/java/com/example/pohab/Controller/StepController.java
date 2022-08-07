package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateStepDto;
import com.example.pohab.DTO.UpdateStepDto;
import com.example.pohab.Entity.Step;
import com.example.pohab.Service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/recruit")
public class StepController {
    private StepService stepService;

    @Autowired
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    // 모집 일정(step) 생성
    @PostMapping("/{party_id}")
    public List<Step> createStep(@PathVariable("party_id") String party_id, @RequestBody ArrayList<CreateStepDto> createStepDto) {
        return this.stepService.createStep(party_id, createStepDto);
    }

    // 모집 일정(step) 전체 읽어오기
    @GetMapping("/step")
    public List<Step> getAllStep() {
        return this.stepService.getAllStep();
    }

    // 모집 일정(step) 소속(party) 별로 읽어오기
    @GetMapping("/step/{party_id}")
    public List<Step> getPartyStep(@PathVariable("party_id") String party_id) {
        return this.stepService.getPartyStep(party_id);
    }

    // 모집 일정(step) 날짜 수정하기
    @PutMapping("/step/{step_id}")
    public Step updateStep(@PathVariable("step_id") Integer step_id, @RequestBody UpdateStepDto updateStepDto) {
        return this.stepService.updateStep(step_id, updateStepDto);
    }

    // 모집 일정(step) 삭제하기
    @DeleteMapping("/step/{step_id}")
    public void deleteStep(@PathVariable("step_id") Integer step_id) {
        this.stepService.deleteStep(step_id);
    }
}
