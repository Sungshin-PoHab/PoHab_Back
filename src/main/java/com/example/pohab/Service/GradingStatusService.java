package com.example.pohab.Service;

import com.example.pohab.DTO.ApplicantDto;
import com.example.pohab.DTO.ApplyStatusForStaffDto;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.GradingStatus;
import com.example.pohab.Entity.Step;
import com.example.pohab.Repository.GradingStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradingStatusService {

    private final GradingStatusRepository gradingStatusRepository;
    private final ApplyStatusService applyStatusService;

    /** 채점 현황 전체 리스트 가져오기 */
    public List<GradingStatus> getAllGradingStatus() {
        return gradingStatusRepository.findAll();
    }

    /** 부서별 & 단계별 채점 현황 가져오기 */
    public List<GradingStatus> getGradingStatusByapplyStatus(ApplyStatus applyStatus) {
        return gradingStatusRepository.getGradingStatusByapplyStatus(applyStatus);
    }

    /** 지원별 채점한 운영진 수 세기 */
    public int countGradedByDepartment(ApplyStatus applyStatus) {
        return gradingStatusRepository.countGradingStatusByApplyId(applyStatus);
    }

    /** 평균 점수 구하기 */
    public double calculAvg(ApplyStatus applyStatus) {
        if (getGradingStatusByapplyStatus(applyStatus).isEmpty()) // 채점을 아무도 하지 않았따면
            return 0.0;
        else
            return gradingStatusRepository.calculAvg(applyStatus);
    }

    /** gradingStatus -> applyStatusForStaffDto */
    public ApplyStatusForStaffDto entityToApplyStatusForStaffDto(Department department, Step step) {

        int applicantNum = applyStatusService.countApplyStatusByDepartment(department);
        double competition = applicantNum / (double) department.getPersonnel(); // 경쟁률

        List<ApplicantDto> applicantDtoList = new ArrayList<>();
        List<ApplyStatus> allApplyStatusByDeNStep = applyStatusService.getAllApplyStatusByDeNStep(department, step); // 부서별 & 단계별 지원 현황

        for (ApplyStatus as : allApplyStatusByDeNStep) {
            double avg = calculAvg(as); // 평균 점수
            ApplicantDto applicantDto = ApplicantDto.builder()
                    .name(as.getUser().getEmail()) // 지원자 이메일
                    .score(avg) // 평균 점수
                    .scoredStaffNum(countGradedByDepartment(as)) // 채점한 운영진 수
                    .build();
            applicantDtoList.add(applicantDto);
        }
        
        ApplyStatusForStaffDto dto = ApplyStatusForStaffDto.builder()
                .party(department.getParty().getId())
                .department(department.getDepartment())
                .step(step.getStep())
                .applicantNum(applicantNum)
                .competition(competition)
                .applicantDtoList(applicantDtoList)
                .build();

        return dto;

    }

}