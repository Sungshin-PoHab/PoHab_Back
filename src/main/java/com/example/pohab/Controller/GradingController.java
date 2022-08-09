package com.example.pohab.Controller;

import com.example.pohab.DTO.ApplyStatusForStaffDto;
import com.example.pohab.DTO.CreateGradingStandardDto;
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

    @GetMapping("/applyStatus/forStaff/{department}/{step}")
    public ApplyStatusForStaffDto applyStatusForStaff(@PathVariable("department") int department, @PathVariable("step") int step) {
        Department departmentById = departmentService.getDepartmentById(department);
        Step stepById = stepService.getStepById(step);
        return  gradingStatusService.entityToApplyStatusForStaffDto(departmentById, stepById);
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
}
