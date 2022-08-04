package com.example.pohab.Service;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.GradingStatus;
import com.example.pohab.Entity.User;
import com.example.pohab.Repository.GradingStatusRepository;
import com.example.pohab.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradingStatusService {

    private final GradingStatusRepository gradingStatusRepository;

    /** 채점 현황 전체 리스트 가져오기 */
    public List<GradingStatus> getAllGradingStatus() {
        return gradingStatusRepository.findAll();
    }

    /** 부서별 채점 현황 가져오기 */
    public List<GradingStatus> getGradingStatusByDepartment(Department department) {
        return gradingStatusRepository.getGradingStatusByDepartment(department);
    }

    /** 지원별 채점한 운영진 수 세기 */
    public int countGradedByDepartment(ApplyStatus applyStatus) {
        return gradingStatusRepository.countGradingStatusByApplyId(applyStatus);
    }

}
