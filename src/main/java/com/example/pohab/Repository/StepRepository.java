package com.example.pohab.Repository;

import com.example.pohab.Entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {
    @Query()
    List<Step> findAllByParty_Id(String party_id);
}
