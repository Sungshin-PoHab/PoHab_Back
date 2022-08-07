package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateDepartmentDto;
import com.example.pohab.DTO.CreateStepDto;
import com.example.pohab.DTO.UpdateStepDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import com.example.pohab.Service.DepartmentService;
import com.example.pohab.Service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/recruit")
public class StepController {
    private StepService stepService;
    private DepartmentService departmentService;

    @Autowired
    public StepController(StepService stepService, DepartmentService departmentService) {
        this.stepService = stepService;
        this.departmentService = departmentService;
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

    // 모집 부서(department) 생성하기
    @PostMapping("/department/{party_id}")
    public List<Department> createDepartment(@PathVariable("party_id") String party_id, @RequestBody ArrayList<CreateDepartmentDto> createDepartmentDtos) {
        return this.departmentService.createDepartment(party_id, createDepartmentDtos);
    }

   // 모집 부서(department) 소속(party) 별로 읽어오기
   @GetMapping("/department/{party_id}")
   public List<Department> getPartyDepartment(@PathVariable("party_id") String party_id) {
        return this.departmentService.getPartyDepartment(party_id);
   }
}
