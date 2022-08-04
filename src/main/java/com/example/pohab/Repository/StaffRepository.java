package com.example.pohab.Repository;

import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    /** 부서별 운영진 인원수 세기 */
    @Query("select count(s) from Staff s where s.department = :department")
    int countExecutivesByDepartment(@Param("department") Department department);

}
