package com.example.pohab.Repository;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Step;
import com.example.pohab.Entity.User;
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

    /** 특정 부서별 지원 인원 세기 */
    @Query("select count(distinct a.user) from ApplyStatus a where a.department = :department")
    int countApplyStatusByDepartment(@Param("department") Department department);

    /** 특정 사용자 & 부서 & 단계의 지원 현황 **/
    @Query ("select a from ApplyStatus a where a.user = :user and a.department = :department and a.step = :step")
    ApplyStatus getApplyStatusByUserDepartmentStep(@Param("user") User user, @Param("department") Department department, @Param("step") Step step);
}