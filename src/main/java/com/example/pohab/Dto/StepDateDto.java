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
    private boolean availability; // 모집 여부
    private LocalDate startDate; // 모집 시작 날짜
    private LocalDate endDate; // 모집 마감 날짜
    private String endTime; // 모집 마감 시간
    private String pmAm; // PM or AM
}