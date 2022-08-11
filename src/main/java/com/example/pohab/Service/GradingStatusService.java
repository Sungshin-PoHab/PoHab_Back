package com.example.pohab.Service;

import com.example.pohab.DTO.ApplicantDto;
import com.example.pohab.DTO.ApplicantGradingDto;
import com.example.pohab.DTO.ApplyStatusForStaffDto;
import com.example.pohab.DTO.GradingResultDto;
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

    /** applyStatus -> applyStatusForStaffDto */
    public ApplyStatusForStaffDto entityToApplyStatusForStaffDto(Department department, Step step) {
        int applicantNum = applyStatusService.countApplyStatusByDepartment(department);
        double competition = applicantNum / (double) department.getPersonnel(); // 경쟁률

        List<ApplicantDto> applicantDtoList = new ArrayList<>();
        List<ApplyStatus> allApplyStatusByDeNStep = applyStatusService.getAllApplyStatusByDeNStep(department, step); // 부서별 & 단계별 지원 현황

        for (ApplyStatus as : allApplyStatusByDeNStep) {
            double avg = calculAvg(as); // 평균 점수
            ApplicantDto applicantDto = ApplicantDto.builder()
                    .name(as.getUser().getName()) // 지원자 이름
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

    /** applyStatus -> GradingResultDto (합격 여부 통보하기) */
    public GradingResultDto announcePNP(Department department, Step step) {
        int applicantNum = applyStatusService.countApplyStatusByDepartment(department); // 지원자 수
        List<ApplyStatus> allApplyStatusByDeNStep = applyStatusService.getAllApplyStatusByDeNStep(department, step); // 부서별 & 단계별 지원 현황

        int beforeGrading = 0; // 채점하기 전 지원자 수
        
        double total = 0.0;
        for (ApplyStatus as : allApplyStatusByDeNStep) {
            if (calculAvg(as) != 0.0) // 평균이 0점인 경우
                beforeGrading++; // 채점하지 않은 것으로 간주 -> 평균 계산 & 최저 점수 계산 시 제외
            total += calculAvg(as); // 전체 지원자 점수 합산
        }

        double scale = Math.pow(10, 2);
        double overallAvg = Math.round(total * scale) / (applicantNum - beforeGrading) / scale; // 전체 평균 (소수 두 번째 자리까지)

        double highScoreAmongAll = highScoreAmongAll(department, step); // 전체 지원자 중 최고 점수
        double lowestScoreAmongAll = lowestScoreAmongAll(department, step); // 전체 지원자 중 최저 점수

        List<ApplicantGradingDto> applicantGradingDtoList = new ArrayList<>();

        for (ApplyStatus as : allApplyStatusByDeNStep) {
            List<GradingStatus> gradingStatus = getGradingStatusByapplyStatus(as); // 지원자별 채점 현황 가져오기
            double highScore = getHighScore(gradingStatus); // 지원자별 최고 점수 가져오기
            double lowestScore = getLowestScore(gradingStatus); // 지원자별 최저 점수 가져오기

            ApplicantGradingDto applicantGradingDto = ApplicantGradingDto.builder()
                    .name(as.getUser().getEmail())
                    .score(calculAvg(as))
                    .highScore(highScore)
                    .lowestScore(lowestScore)
                    .build();

            applicantGradingDtoList.add(applicantGradingDto);
        }

        GradingResultDto gradingResultDto = GradingResultDto.builder()
                .party(department.getParty().getId())
                .department(department.getDepartment())
                .step(step.getStep())
                .overallAvg(overallAvg)
                .highScore(highScoreAmongAll)
                .lowestScore(lowestScoreAmongAll)
                .applicantGradingDtoList(applicantGradingDtoList)
                .build();

        return gradingResultDto;
    }

    /** 지원자별 최고 점수 가져오기 */
    private double getHighScore(List<GradingStatus> gradingStatus) {
        double highScore = 0.0; // 지원자별 가장 높은 점수
        for (GradingStatus gs : gradingStatus) {
            if (highScore < gs.getScore())
                highScore = gs.getScore();
        }
        return highScore;
    }

    /** 지원자별 최저 점수 가져오기 */
    private double getLowestScore(List<GradingStatus> gradingStatus) {
        double lowestScore = 101.0; // 지원자별 가장 낮은 점수
        for (GradingStatus gs : gradingStatus) {
            if (gs.getScore() == 0.0) continue; // 채점하지 않은 경우 제외
            if (lowestScore > gs.getScore())
                lowestScore = gs.getScore();
        }
        if (lowestScore == 101.0) lowestScore = 0.0; // 아무도 채점하지 않은 것으로 간주
        return lowestScore;
    }

    /** 지원자 중 최고 점수 가져오기 */
    public double highScoreAmongAll(Department department, Step step) {
        List<ApplyStatus> allApplyStatusByDeNStep = applyStatusService.getAllApplyStatusByDeNStep(department, step); // 부서별 & 단계별 지원 현황
        double highScore = 0.0;
        for (ApplyStatus as : allApplyStatusByDeNStep) {
            if (highScore < calculAvg(as))
                highScore = calculAvg(as);
        }
        return highScore;
    }

    /** 지원자 중 최저 점수 가져오기  */
    public double lowestScoreAmongAll(Department department, Step step) {
        List<ApplyStatus> allApplyStatusByDeNStep = applyStatusService.getAllApplyStatusByDeNStep(department, step); // 부서별 & 단계별 지원 현황
        double lowestScore = 100.0;
        for (ApplyStatus as : allApplyStatusByDeNStep) {
            if (lowestScore > calculAvg(as))
                lowestScore = calculAvg(as);
        }
        return lowestScore;
    }

}