package com.example.pohab.DTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradingResultDto {
    private String party; // 소속 + 기수
    private String department; // 부서
    private int step; // 단계
    private double overallAvg; // 전체 평균
    private double highScore; // 지원자 중 최고 점수
    private double lowestScore; // 지원자 중 최저 점수
    private List<ApplicantGradingDto> applicantGradingDtoList;
}
