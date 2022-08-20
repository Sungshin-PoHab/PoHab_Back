package com.example.pohab.Service;

import com.example.pohab.DTO.ApplyUserForPartyDTO;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import com.example.pohab.Entity.User;
import com.example.pohab.Enum.IsSubmit;
import com.example.pohab.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    /** 부서별 & 단계별 지원 현황 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatusByDeNStepSubmit(Department department, Step step) {
        return applyStatusRepository.getAllApplyStatusByDeNStepSubmit(department, step, IsSubmit.submission);
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

        User user = this.userRepository.findById(applyUserForPartyDTO.getUser()).orElse(null);
        Department department = this.departmentRepository.findById(applyUserForPartyDTO.getDepartment()).orElse(null);
        Step step = this.stepRepository.findById(applyUserForPartyDTO.getStep()).orElse(null);

        try{
            applystatus.setUser(user);
            applystatus.setDepartment(department);
            applystatus.setStep(step);
            return applyStatusRepository.save(applystatus);

        } catch (Exception e){
            return null;
        }

    }

    /** 최종 제출 하기 **/
    public ApplyStatus statusUpdateToSubmit(Integer user_id, Integer department_id, Integer step_id){
        User user = this.userRepository.findById(user_id).orElse(null);
        Department department = this.departmentRepository.findById(department_id).orElse(null);
        Step step = this.stepRepository.findById(step_id).orElse(null);

        try {
            //기존의 applyStatus 불러오기
            ApplyStatus applyStatus = this.applyStatusRepository.getApplyStatusByUserDepartmentStep(user, department, step);
            //제출 시각과 상태 저장
            applyStatus.setSub_date( LocalDateTime.now() );
            applyStatus.setIs_submit(IsSubmit.submission);
            //수정 사항 저장
            applyStatusRepository.save(applyStatus);
            return applyStatus;
        } catch (Exception e) {
            return null;
        }
    }

    /** 합격 처리하기 */
    @Transactional
    public void updatePass(ApplyStatus applyStatus) {
        applyStatus.updatePass();
    }
}