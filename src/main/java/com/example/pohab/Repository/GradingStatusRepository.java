package com.example.pohab.Repository;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.GradingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradingStatusRepository extends JpaRepository<GradingStatus, Integer> {

    /** 부서별 & 단계별 채점 현황 */
    @Query("select gs from GradingStatus gs where gs.apply_status.department = :department")
    List<GradingStatus> getGradingStatusByDepartment(@Param("department") Department department);

    /** 지원별 채점한 운영진 수 세기 */
    @Query("select count(gs) from GradingStatus gs where gs.apply_status = :applyStatus")
    int countGradingStatusByApplyId(@Param("applyStatus") ApplyStatus applyStatus);

}
