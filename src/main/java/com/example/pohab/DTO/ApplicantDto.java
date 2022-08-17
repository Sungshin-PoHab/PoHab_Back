package com.example.pohab.DTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {
    private Integer applyId; // 지원 현황 아이디
    private String name; // 지원자 이름
    private double score; // 점수
    private Integer scoredStaffNum; // 채점한 운영진 수
}
