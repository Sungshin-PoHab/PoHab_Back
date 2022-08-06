package com.example.pohab.Dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {
    private String name; // 지원자 이름
    private double score; // 점수
    private int scoredStaffNum; // 채점한 운영진 수
}
