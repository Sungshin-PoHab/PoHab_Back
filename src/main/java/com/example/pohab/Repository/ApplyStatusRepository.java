package com.example.pohab.Repository;

import com.example.pohab.Entity.*;
import com.example.pohab.Enum.IsSubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ApplyStatusRepository extends JpaRepository<ApplyStatus, Integer> {
    /** 특정 사용자의 지원 현황 */
    @Query("select a from ApplyStatus a where a.user = :user")
    List<ApplyStatus> getAllApplyStatusByUser(@Param("user") User user);

    /** 특정 부서의 지원 현황 */
    @Query("select a from ApplyStatus a where a.department = :department")
    List<ApplyStatus> getAllApplyStatusByDepartment(@Param("department") Department department);

    /** 특정 부서 & 특정 단계의 지원 현황 */
    @Query("select a from ApplyStatus a where a.department = :department and a.step = :step")
    List<ApplyStatus> getAllApplyStatusByDeNStep(@Param("department") Department department, @Param("step") Step step);

    /** 특정 부서 & 특정 단계 & 제출 완료한 지원 현황 */
    @Query("select a from ApplyStatus a where a.department = :department and a.step = :step and a.is_submit = :submission")
    List<ApplyStatus> getAllApplyStatusByDeNStepSubmit(@Param("department") Department department, @Param("step") Step step, @Param("submission") IsSubmit submission);

    /** 특정 부서별 지원 인원 세기 */
    @Query("select count(distinct a.user) from ApplyStatus a where a.department = :department")
    int countApplyStatusByDepartment(@Param("department") Department department);

    /** 특정 사용자 & 부서 & 단계의 지원 현황 **/
    @Query ("select a from ApplyStatus a where a.user = :user and a.department = :department and a.step = :step")
    ApplyStatus getApplyStatusByUserDepartmentStep(@Param("user") User user, @Param("department") Department department, @Param("step") Step step);

    @Query (value = "select d.party_id from apply_status a join department d on a.department_id = d.id where a.id= :apply_status_id", nativeQuery = true)
    String getPartyFromApplyStatus(@Param("apply_status_id") Integer apply_status_id);
}