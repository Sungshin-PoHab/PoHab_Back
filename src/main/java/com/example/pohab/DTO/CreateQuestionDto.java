package com.example.pohab.DTO;

import lombok.Data;

@Data
public class CreateQuestionDto {
    private Integer departmentId;
    private String question;
    private Integer maxLength;
}
