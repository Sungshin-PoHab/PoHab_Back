package com.example.pohab.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepDateDto {
    private int step;
    private LocalDate startDate; // 모집 시작 날짜
    private LocalDateTime endDate; // 모집 마감 날짜 + 시간
}
