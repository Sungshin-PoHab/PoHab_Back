package com.example.pohab.Service;

import com.example.pohab.Dto.MainDto;
import com.example.pohab.Dto.StepDateDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import com.example.pohab.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final StepService stepService;
    private final ApplyStatusService applyStatusService;

    /** 부서 전체 리스트 가져오기 */
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    /** 기본키로 특정 부서 가져오기 */
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).get();
    }

    /** department -> mainDto */
    public List<MainDto> entityToMainDto() {
        List<Department> allDepartment = getAllDepartment();
        List<Step> allStep = stepService.getAllStep();

        List<StepDateDto> stepDateDtos = new ArrayList<>();
        for (Step step : allStep) {
            StepDateDto stepDateDto = StepDateDto.builder()
                    .step(step.getStep())
                    .startDate(step.getStart_date())
                    .endDate(step.getEnd_date())
                    .build();
            stepDateDtos.add(stepDateDto);
        }

        List<MainDto> mainDtos = new ArrayList<>();

        for (Department department : allDepartment) {
            int 지원자_수 = applyStatusService.countApplyStatusByDepartment(department);
            System.out.println("지원자 수: " + 지원자_수);
            double 경쟁률 = 지원자_수 / (double) department.getPersonnel();
            System.out.println("모집 인원 수: " + department.getPersonnel());
            System.out.println("경쟁률: " + 경쟁률);
            MainDto mainDto = MainDto.builder()
                    .party(department.getParty().getName())
                    .competition(경쟁률)
                    .stepDateDtos(stepDateDtos)
                    .build();
            mainDtos.add(mainDto);
        }

        return mainDtos;
    }

}