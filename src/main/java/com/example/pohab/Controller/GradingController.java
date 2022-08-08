package com.example.pohab.Controller;

import com.example.pohab.DTO.ApplyStatusForStaffDto;
import com.example.pohab.DTO.GradingResultDto;
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
public class GradingController {

    private final GradingStatusService gradingStatusService;
    private final DepartmentService departmentService;
    private final StepService stepService;

    /** 합격 여부 통보 */
    @GetMapping("/gradingStatus/announcePNP/{department}/{step}")
    public GradingResultDto announcePNP(@PathVariable("department") int department, @PathVariable("step") int step) {
        Department departmentById = departmentService.getDepartmentById(department);
        Step stepById = stepService.getStepById(step);
        return  gradingStatusService.announcePNP(departmentById, stepById);
    }

}
