package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateGradingStandardDto;
import com.example.pohab.DTO.GradingResultDto;
import com.example.pohab.DTO.SendEmailDto;
import com.example.pohab.DTO.UpdateGradingStandardDto;
import com.example.pohab.Entity.*;
import com.example.pohab.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GradingController {

    private final GradingStatusService gradingStatusService;
    private final DepartmentService departmentService;
    private final StepService stepService;
    private final GradingStandardService gradingStandardService;

    /** 합격 여부 통보 (Get) */
    @GetMapping("/grading/announcePNP/{department}/{step}")
    public GradingResultDto announcePNP(@PathVariable("department") int department, @PathVariable("step") int step) {
        Department departmentById = departmentService.getDepartmentById(department);
        Step stepById = stepService.getStepById(step);
        return  gradingStatusService.announcePNP(departmentById, stepById);
    }

    /** 합격 여부 통보 (Post) */
    @PostMapping("/grading/announcePNP/{department}/{step}")
    public SendEmailDto announcePNPPost(@PathVariable("department") int department,
                                        @PathVariable("step") int step, @RequestBody SendEmailDto emailDto) {
        System.out.println(emailDto);
        return gradingStatusService.getSendEmailDto(emailDto);
    }

    // 채점 양식(Grading Standard) 등록
    @PostMapping("/standard/{step_id}")
    public List<GradingStandard> createGradingStandard(@PathVariable("step_id") Integer step_id, @RequestParam("department") Integer department_id, @RequestBody() List<CreateGradingStandardDto> createGradingStandardDtos) {
        return this.gradingStandardService.createGradingStandard(step_id, department_id, createGradingStandardDtos);
    }

    // 채점 양식(grading Standard) 단계+부서 별로 읽어오기
    @GetMapping("/standard/{step_id}")
    public List<GradingStandard> getGradingStandardByStepDepartment(@PathVariable("step_id") Integer step_id, @RequestParam("department") Integer department_id) {
        return this.gradingStandardService.getGradingStandardByStepDepartment(step_id, department_id);
    }

    // 채점 양식(grading Standard) 수정하기
    @PutMapping("/standard/{standard_id}")
    public GradingStandard updateGradingStandard(@PathVariable("standard_id") Integer standard_id, @RequestBody() UpdateGradingStandardDto updateGradingStandardDto) {
        return this.gradingStandardService.updateGradingStandard(standard_id, updateGradingStandardDto);
    }

    // 채점 양식(grading standard) 삭제하기
    @DeleteMapping("/standard/{standard_id}")
    public void deleteGradingStandard(@PathVariable("standard_id") Integer standard_id) {
        this.gradingStandardService.deleteGradingStandard(standard_id);
    }

}
