package com.example.pohab.Repository;

import com.example.pohab.Entity.GradingStandard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradingStandardRepository extends JpaRepository<GradingStandard, Integer> {
    List<GradingStandard> findAllByStep_idAndDepartment_id(Integer step_id, Integer department_id);

    List<GradingStandard> findAllByStep_id(Integer step_id);
}
