package com.example.pohab.Service;

import com.example.pohab.DTO.CreateDepartmentDto;
import com.example.pohab.DTO.CreateGradingStandardDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.GradingStandard;
import com.example.pohab.Entity.Step;
import com.example.pohab.Repository.DepartmentRepository;
import com.example.pohab.Repository.GradingStandardRepository;
import com.example.pohab.Repository.StepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GradingStandardService {
    private final StepRepository stepRepository;
    private final DepartmentRepository departmentRepository;
    private final GradingStandardRepository gradingStandardRepository;

    // 채점 양식(Grading standard) 등록하기
    public List<GradingStandard> createGradingStandard(Integer step_id, Integer department_id, List<CreateGradingStandardDto> createGradingStandardDtos) {
        ArrayList<GradingStandard> gradingStandards = new ArrayList<>();

        Step step = this.stepRepository.findById(step_id).orElse(null);
        Department department = this.departmentRepository.findById(department_id).orElse(null);

        if (step == null || department == null) {
            // 추후 에러처리 필요
            System.out.println("error");
            return null;
        } else {
            for (CreateGradingStandardDto gradingStandardDto: createGradingStandardDtos) {
                GradingStandard gradingStandard = new GradingStandard();
                gradingStandard.setStep(step);
                gradingStandard.setDepartment(department);
                gradingStandard.setGradingStandard(gradingStandardDto.getGradingStandard());
                gradingStandards.add(gradingStandard);
            }
        }

        return this.gradingStandardRepository.saveAll(gradingStandards);
    }
}
