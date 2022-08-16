package com.example.pohab.DTO;

import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyStatusForStaffDto {
    private String party; // 소속 + 기수
    private String department; // 부서
    private int departmentId; // 부서 아이디
    private int step; // 단계
    private int applicantNum; // 지원 인원
    private double competition; // 경쟁률
    private List<ApplicantDto> applicantDtoList; // 지원자 리스트
    private List<Department> departmentDtoList; // 부서 리스트
    private List<Step> stepDtoList; // 소속별 단계 리스트
}
