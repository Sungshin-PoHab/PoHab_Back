package com.example.pohab.DTO;

import com.example.pohab.Entity.Staff;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartyListForStaffDto {
    String partyId; // 소속
    String role; // 역할
    Integer departmentId; // '공통' 부서의 아이디
    Integer stepId; // '공통' 부서의 아이디
}
