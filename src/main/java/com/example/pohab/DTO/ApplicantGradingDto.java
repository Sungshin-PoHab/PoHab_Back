package com.example.pohab.DTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantGradingDto {
    private int applyId; // applyStatus 아이디
    private String name; // 지원자 이름
    private double score; // 점수
    private double highScore; // 최고 점수
    private double lowestScore; // 최저 점수
}
