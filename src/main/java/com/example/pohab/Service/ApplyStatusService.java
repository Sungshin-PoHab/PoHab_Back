package com.example.pohab.Service;

import com.example.pohab.DTO.ApplyUserForPartyDTO;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import com.example.pohab.Entity.User;
import com.example.pohab.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyStatusService {

    private ApplyStatusRepository applyStatusRepository;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private StepRepository stepRepository;

    @Autowired
    public ApplyStatusService(ApplyStatusRepository applyStatusRepository, UserRepository userRepository, DepartmentRepository departmentRepository, StepRepository stepRepository){
        this.applyStatusRepository = applyStatusRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.stepRepository = stepRepository;
    }
    /** 지원 현황 전체 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatus() {
        return applyStatusRepository.findAll();
    }

    /** 기본키로 특정 지원 현황 가져오기 */
    public ApplyStatus getApplyStatusById(int id) {
        return applyStatusRepository.findById(id).get();
    }

    /** 사용자별 지원 현황 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatusByUser(User user) {
        return applyStatusRepository.getAllApplyStatusByUser(user);
    }

    /** 부서별 지원 현황 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatusByDepartment(Department department) {
        return applyStatusRepository.getAllApplyStatusByDepartment(department);
    }

    /** 부서별 & 단계별 지원 현황 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatusByDeNStep(Department department, Step step) {
        return applyStatusRepository.getAllApplyStatusByDeNStep(department, step);
    }

    /** 부서별 지원 인원 세기 */
    public int countApplyStatusByDepartment(Department department) {
        return applyStatusRepository.countApplyStatusByDepartment(department);
    }

    /** applyStatus -> ApplyStatusForManagerDto */
    public void entityToApplyManagerDto(Department department) {
        List<ApplyStatus> allApplyStatusByDepartment = getAllApplyStatusByDepartment(department);

    }

    /** 부서에 지원하기 **/
    public ApplyStatus applyUserTForParty(ApplyUserForPartyDTO applyUserForPartyDTO) {
        ApplyStatus applystatus = new ApplyStatus();

        applystatus.setUser(userRepository.getOne(applyUserForPartyDTO.getUser()));
        applystatus.setDepartment(departmentRepository.getOne(applyUserForPartyDTO.getDepartment()));
        applystatus.setStep(stepRepository.getOne(applyUserForPartyDTO.getStep()));
        return applyStatusRepository.save(applystatus);
    }

}