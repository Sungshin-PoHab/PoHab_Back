package com.example.pohab.Dto;

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
    private int applicant; // 지원 인원
    private int competition; // 경쟁률
    private List<ApplicantDto> applicantDtoList; // 지원자 리스트
}
