package com.example.pohab.Repository;

import com.example.pohab.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByDepartment_id(Integer department_id);

    List<Question> findAllByDepartment_Party_id(String party_id);
}
