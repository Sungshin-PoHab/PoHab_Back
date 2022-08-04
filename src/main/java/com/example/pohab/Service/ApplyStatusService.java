package com.example.pohab.Service;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.User;
import com.example.pohab.Repository.ApplyStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyStatusService {

    private final ApplyStatusRepository applyStatusRepository;

    /** 지원 현황 전체 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatus() {
        return applyStatusRepository.findAll();
    }

    /** 사용자별 지원 현황 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatusByUser(User user) {
        return applyStatusRepository.getAllApplyStatusByUser(user);
    }

    /** 부서별 지원 인원 세기 */
    public int countApplyStatusByDepartment(Department department) {
        return applyStatusRepository.countApplyStatusByDepartment(department);
    }

}
