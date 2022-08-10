package com.example.pohab.DTO;

import com.example.pohab.Enum.IsPass;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantGradingDto {
    private String name; // 지원자 이름
    private double score; // 점수
    private double highScore; // 최고 점수
    private double lowestScore; // 최저 점수
}
