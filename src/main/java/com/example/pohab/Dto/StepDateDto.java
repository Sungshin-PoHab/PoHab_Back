package com.example.pohab.DTO;

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
    private LocalDateTime startDate; // 모집 시작 날짜
    private LocalDateTime endDate; // 모집 마감 날짜 + 시간
}