package com.example.pohab.DTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainDto {
    private String party; // 소속명
    private double competition; // 경쟁률
    List<StepDateDto> stepDateDtos; // 단계 + 모집 날짜
}
