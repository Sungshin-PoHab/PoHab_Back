package com.example.pohab.Repository;

import com.example.pohab.Entity.Grading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradingRepository extends JpaRepository<Grading, Integer> {
    @Query("select grading from Grading grading where grading.grading_status.id = ?1")
    List<Grading> findAllByGrading_status_id(Integer grading_status_id);

}
