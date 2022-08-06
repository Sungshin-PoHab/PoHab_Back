package com.example.pohab.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateStepDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
