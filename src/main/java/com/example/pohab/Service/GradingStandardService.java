package com.example.pohab.Service;

import com.example.pohab.DTO.CreateDepartmentDto;
import com.example.pohab.DTO.CreateGradingStandardDto;
import com.example.pohab.DTO.UpdateGradingStandardDto;
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
    public List<GradingStandard> createGradingStandard(Integer step_id, List<CreateGradingStandardDto> createGradingStandardDtos) {
        ArrayList<GradingStandard> gradingStandards = new ArrayList<>();

        Step step = this.stepRepository.findById(step_id).orElse(null);

        if (step == null) {
            // 추후 에러처리 필요
            System.out.println("error");
            return null;
        } else {
            for (CreateGradingStandardDto gradingStandardDto: createGradingStandardDtos) {
                GradingStandard gradingStandard = new GradingStandard();
                Department department = this.departmentRepository.findById(gradingStandardDto.getDepartmentId()).orElse(null);

                if (department == null) {
                    // 추후 에러처리 필요
                    System.out.println("error");
                    return null;
                }
                gradingStandard.setStep(step);
                gradingStandard.setDepartment(department);
                gradingStandard.setGradingStandard(gradingStandardDto.getGradingStandard());
                gradingStandards.add(gradingStandard);
            }
        }

        return this.gradingStandardRepository.saveAll(gradingStandards);
    }

    // 채점 양식(grading Standard) 단계+부서 별로 읽어오기
    public List<GradingStandard> getGradingStandardByStepDepartment(Integer step_id, Integer department_id) {
        return this.gradingStandardRepository.findAllByStep_idAndDepartment_id(step_id, department_id);
    }

    // 채점 양식(grading Standard) 수정하기
    public GradingStandard updateGradingStandard(Integer standard_id, UpdateGradingStandardDto updateGradingStandardDto) {
        GradingStandard update_standard = this.gradingStandardRepository.findById(standard_id).orElse(null);

        if (update_standard == null) {
            // 추후 에러처리 필요
            System.out.println("error");
            return null;
        } else {
            update_standard.setGradingStandard(updateGradingStandardDto.getGradingStandard());
            return this.gradingStandardRepository.save(update_standard);
        }
    }

    // 채점 양식(grading standard) 삭제하기
    public void deleteGradingStandard(Integer standard_id) {
        this.gradingStandardRepository.deleteById(standard_id);
    }
}
