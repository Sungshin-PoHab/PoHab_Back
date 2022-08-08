package com.example.pohab.Repository;

import com.example.pohab.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAllByParty_Id(String party_id);
}
