package com.example.pohab.Controller;

import com.example.pohab.DTO.ApplyStatusForStaffDto;
import com.example.pohab.Entity.*;
import com.example.pohab.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.pohab.DTO.ApplyUserForPartyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/apply")
public class ApplyController {

    private ApplyStatusService applyStatusService;
    private AnswerService answerService;
    private GradingStatusService gradingStatusService;
    private DepartmentService departmentService;
    private StepService stepService;

    @Autowired
    public ApplyController(ApplyStatusService applyStatusService, AnswerService answerService, GradingStatusService gradingStatusService, DepartmentService departmentService, StepService stepService) {
        this.applyStatusService = applyStatusService;
        this.answerService = answerService;
        this.gradingStatusService = gradingStatusService;
        this.departmentService = departmentService;
        this.stepService = stepService;
    }

    // 동아리 지원 -> apply_status에 저장 및 answer null로 초기화.
    @PostMapping("/saveStatus")
    public ApplyStatus saveStatus(@RequestBody ApplyUserForPartyDTO applyUserForPartyDTO) {
        ApplyStatus applyStatus = this.applyStatusService.applyUserTForParty(applyUserForPartyDTO);
        //지원한 소속
        Party party = applyStatus.getDepartment().getParty();
        //지원한 부서에 대한 답변 Null로 초기화
        this.answerService.saveEmptyAnswers(applyStatus, applyStatus.getDepartment().getId());
        //공통&개인정보&설명 부서에 대한 답변 Null로 초기화
        List<Department> departments = this.departmentService.findCommonDepartmentByParty(party.getId());

        for (Department department : departments){
            if(!department.getDepartment().equals("설명"))
                this.answerService.saveEmptyAnswers(applyStatus, department.getId());
        }

        return applyStatus;
    }

    /** 최종 지원 (지원서 제출) **/
    @PostMapping("/{department_id}/{step_id}")
    public ApplyStatus submitAnswer(@PathVariable("department_id") Integer department_id, @PathVariable("step_id") Integer step_id, @RequestBody List<Answer> answers){
        this.answerService.tempSaveAnswer(answers);
        //로그인 구현 후에 user 수정해야함
        return this.applyStatusService.statusUpdateToSubmit(1, department_id, step_id);
    }
  
    /** (운영진 ver.) 지원 현황 */
    @GetMapping("/forStaff/{department}/{step}")
    public ApplyStatusForStaffDto applyStatusForStaff(@PathVariable("department") int department, @PathVariable("step") int step) {
        Department departmentById = departmentService.getDepartmentById(department);
        Step stepById = stepService.getStepById(step);
        return  gradingStatusService.entityToApplyStatusForStaffDto(departmentById, stepById);
    }

    /** 동아리 지원 내역 **/
    @PostMapping("/myApply")
    public List<ApplyStatus> myApply(@RequestBody User user) {
        return applyStatusService.getAllApplyStatusByUser(user);
    }

}
