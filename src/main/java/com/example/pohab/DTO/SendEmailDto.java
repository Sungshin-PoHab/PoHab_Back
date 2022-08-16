package com.example.pohab.DTO;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailDto {
    private List<String> email;
    private String title;
    private String text;
}
