package com.example.pohab.Service;

import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Party;
import com.example.pohab.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    /** 부서별 운영진 인원수 세기 */
    public int countExecutivesByDepartment(Department department) {
        return staffRepository.countExecutivesByDepartment(department);
    }

}
