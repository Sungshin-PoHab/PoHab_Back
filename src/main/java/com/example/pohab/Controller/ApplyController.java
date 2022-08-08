package com.example.pohab.Controller;

import com.example.pohab.DTO.ApplyStatusForStaffDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import com.example.pohab.Service.DepartmentService;
import com.example.pohab.Service.GradingStatusService;
import com.example.pohab.Service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApplyController {

    private final GradingStatusService gradingStatusService;
    private final DepartmentService departmentService;
    private final StepService stepService;

    /** (운영진 ver.) 지원 현황 */
    @GetMapping("/applyStatus/forStaff/{department}/{step}")
    public ApplyStatusForStaffDto applyStatusForStaff(@PathVariable("department") int department, @PathVariable("step") int step) {
        Department departmentById = departmentService.getDepartmentById(department);
        Step stepById = stepService.getStepById(step);
        return  gradingStatusService.entityToApplyStatusForStaffDto(departmentById, stepById);
    }

}
