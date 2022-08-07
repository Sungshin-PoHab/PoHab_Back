package com.example.pohab.Service;

import com.example.pohab.DTO.CreateDepartmentDto;
import com.example.pohab.DTO.MainDto;
import com.example.pohab.DTO.StepDateDto;
import com.example.pohab.DTO.UpdateDepartmentDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Step;
import com.example.pohab.Repository.DepartmentRepository;
import com.example.pohab.Repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final PartyRepository partyRepository;
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
                    .startDate(step.getStartDate())
                    .endDate(step.getEndDate())
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


    // 모집 부서(department) 생성
    public List<Department> createDepartment(String party_id, ArrayList<CreateDepartmentDto> createDepartmentDtos) {
        ArrayList<Department> departments = new ArrayList<>();
        Party party = this.partyRepository.findById(party_id).orElse(null);

        if (party == null) {
            // 추후 에러처리 필요
            System.out.println("error");
            // 임시 반환값 꼭 수정!
            return null;
        } else {
            for (CreateDepartmentDto departmentDto: createDepartmentDtos) {
                Department department = new Department();
                department.setParty(party);
                department.setDepartment(departmentDto.getDepartment());
                department.setPersonnel(departmentDto.getPersonnel());
                departments.add(department);
            }
            return this.departmentRepository.saveAll(departments);
        }
    }

    // 모집 부서(department) 소속(party) 별로 읽어오기
    public List<Department> getPartyDepartment(String party_id) {
        return this.departmentRepository.findAllByParty_Id(party_id);
    }

    // 모집 부서(department) 수정하기
    public Department updateDepartment(Integer department_id, UpdateDepartmentDto updateDepartmentDto) {
        Department update_department = this.departmentRepository.findById(department_id).orElse(null);

        if (update_department == null) {
            // 추후 오류 처리 필요
            System.out.println("error");
            return null;
        } else {
            update_department.setDepartment(updateDepartmentDto.getDepartment());
            update_department.setPersonnel(updateDepartmentDto.getPersonnel());
            return this.departmentRepository.save(update_department);
        }
    }
}