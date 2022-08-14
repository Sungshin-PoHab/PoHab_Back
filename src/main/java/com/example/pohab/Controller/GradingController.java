package com.example.pohab.Controller;

import com.example.pohab.DTO.*;
import com.example.pohab.DTO.CreateGradingStandardDto;
import com.example.pohab.DTO.GradingResultDto;
import com.example.pohab.DTO.UpdateGradingStandardDto;
import com.example.pohab.Entity.*;
import com.example.pohab.Repository.AnswerRepository;
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
    private final GradingService gradingService;
    private final AnswerRepository answerRepository;
    private final ApplyStatusService applyStatusService;

    /** 합격 여부 통보 (Get) */
    @GetMapping("/grading/announcePNP/{department}/{step}")
    public GradingResultDto announcePNP(@PathVariable("department") int department, @PathVariable("step") int step) {
        Department departmentById = departmentService.getDepartmentById(department);
        Step stepById = stepService.getStepById(step);
        return  gradingStatusService.announcePNP(departmentById, stepById);
    }

    /** 합격 여부 통보 (Post) */
    @PostMapping("/grading/announcePNP/{department}/{step}")
    public List<String> announcePNPPost(@PathVariable("department") int department,
                                @PathVariable("step") int step, @RequestBody List<String> passList) {
        return gradingStatusService.updatePassList(passList);
    }

    // 채점 양식(Grading Standard) 등록
    @PostMapping("/standard/{step_id}")
    public List<GradingStandard> createGradingStandard(@PathVariable("step_id") Integer step_id, @RequestBody() List<CreateGradingStandardDto> createGradingStandardDtos) {
        return this.gradingStandardService.createGradingStandard(step_id, createGradingStandardDtos);
    }

    // 채점 양식(grading Standard) 단계별로 읽어오기
    @GetMapping("/standard/{step_id}")
    public List<GradingStandard> getGradingStandardByStepDepartment(@PathVariable("step_id") Integer step_id) {
        return this.gradingStandardService.getGradingStandardByStep(step_id);
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

    // 채점(Grading) 등록하기
    @PostMapping("grading/{apply_id}")
    public void createGrading(@PathVariable("apply_id") Integer apply_id, @RequestBody()List<CreateGradingDto> createGradingDtos) {
        this.gradingService.createGrading(apply_id, createGradingDtos);
    }

    // 채점을 위해 지원현황별로 answer 불러오기
    @GetMapping("/grading/apply/{apply_id}")
    public List<Answer> getAnswerByApplyStatus(@PathVariable("apply_id") Integer apply_id) {
        return this.answerRepository.findAllyByApplyStatus_id(apply_id);
    }

    // 채점(Grading) 수정하기
    @PutMapping("/grading/{grading_id}")
    public Grading updateGrading(@PathVariable("grading_id") Integer grading_id, @RequestBody() UpdateGradingDto updateGradingDto) {
        return this.gradingService.updateGrading(grading_id, updateGradingDto);
    }
}
