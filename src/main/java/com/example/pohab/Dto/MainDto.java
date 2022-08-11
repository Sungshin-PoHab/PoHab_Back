package com.example.pohab.DTO;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainDto {
    private String party; // 소속명
    private List<String> competition; // 경쟁률
    private boolean availability; // 모집 여부
    private List<String> department; // 모집 부서 리스트
    private List<StepDateDto> stepDateDtos; // 단계 + 모집 날짜
}
