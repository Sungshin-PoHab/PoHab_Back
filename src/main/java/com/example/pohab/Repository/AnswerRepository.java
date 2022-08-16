package com.example.pohab.Repository;

import com.example.pohab.Entity.Answer;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllyByApplyStatus_id(Integer apply_id);

    @Query("select a from Answer a where a.applyStatus = :applyStatus")
    List<Answer> getAllAnswersByApplyStatusId (@Param("applyStatus") ApplyStatus applyStatus);
}
