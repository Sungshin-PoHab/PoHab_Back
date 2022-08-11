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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final PartyRepository partyRepository;
    private final PartyService partyService;
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

    /** party(소속)별 department(모집 부서) 가져오기 */
    public List<Department> findDepartmentsByParty(Party party) {
        return departmentRepository.findDepartmentsByParty(party);
    }

    /** department -> mainDto */
    public List<MainDto> entityToMainDto() {
        List<Party> allParty = partyService.findAll();
        List<Department> allDepartment = new ArrayList<>();
        List<MainDto> mainDtos = new ArrayList<>();

        for (Party party : allParty) {

            // 부서별 경쟁률
            allDepartment = findDepartmentsByParty(party);
            List<String> competitionList = new ArrayList<>();
            for (Department department : allDepartment) {
                int applicantNum = applyStatusService.countApplyStatusByDepartment(department);
                double competition = applicantNum / (double) department.getParty().getPersonnel();

                double scale = Math.pow(10, 2);
                double competitionTwo = Math.round(competition * scale) / scale; // 소수 두 번째 자리까지

                String stringCompertition = department.getDepartment() + " 부서 경쟁률: " + competitionTwo + " : 1";
                competitionList.add(stringCompertition);
            }

            List<StepDateDto> stepDateDtos = new ArrayList<>();

            List<Step> allStep = stepService.getPartyStep(party);
            List<Boolean> availabilitys = new ArrayList<>();
            for (Step step : allStep) {
                boolean stepAvail = true;
                LocalDateTime endDate = step.getEndDate();
                if (endDate.isBefore(LocalDateTime.now())) // 모집 마감 시간이 현재 시간 전이라면
                    stepAvail = false; // 모집 마감 처리
                availabilitys.add(stepAvail);

                // 시간
                int hour = step.getEndDate().getHour();
                String stringHour = String.valueOf(hour);
                // 분
                int minute = step.getEndDate().getMinute();
                String stringMinute = String.valueOf(minute);
                if (minute == 0) stringMinute = "00";
                // 오전/오후
                String pmam = (hour >= 12) ? "PM" : "AM";
                if (hour > 12) hour -= 12; // 13시 -> 오후 1시
                if (hour < 10) stringHour = "0" + hour;
                String endTime = stringHour + ":" + stringMinute;

                StepDateDto stepDateDto = StepDateDto.builder()
                        .step(step.getStep())
                        .availability(stepAvail)
                        .startDate(step.getStartDate().toLocalDate())
                        .endDate(step.getEndDate().toLocalDate())
                        .endTime(endTime)
                        .pmAm(pmam)
                        .build();
                stepDateDtos.add(stepDateDto);
            }

            boolean availability = false;
            if (availabilitys.contains(true)) availability = true;

            MainDto mainDto = MainDto.builder()
                    .party(party.getId())
                    .competition(competitionList)
                    .availability(true)
                    .availability(availability)
                    .department(findDepartmentsNameByParty(party))
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

    // 모집 부서(department) 소속(party) 별로 읽어오기 -> String 타입 리스트
    public List<String> findDepartmentsNameByParty(Party party) {
        return this.departmentRepository.findDepartmentsNameByParty(party);
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

    public void deleteDepartment(Integer department_id) {
        this.departmentRepository.deleteById(department_id);
    }
}