package com.example.pohab.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateStepDto {
    private Integer step;
    private LocalDateTime end_date;
}
