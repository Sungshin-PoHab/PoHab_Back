package com.example.pohab.DTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyStatusForStaffDto {
    private String party; // 소속 + 기수
    private String department; // 부서
    private int step; // 단계
    private int applicantNum; // 지원 인원
    private double competition; // 경쟁률
    private List<ApplicantDto> applicantDtoList; // 지원자 리스트
}
