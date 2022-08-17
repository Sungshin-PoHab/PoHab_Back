package com.example.pohab.DTO;

import com.example.pohab.Entity.Staff;
import lombok.Data;

@Data
public class ChatDto {
    private Integer applyId;
    private Integer writerId;
    private String chat;
    private String name;
    private String role;
}
