package com.example.pohab.Repository;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAllByParty_Id(String party_id);
    List<Department> findDepartmentsByParty(Party party);

    /** 소속별 모집 부서 리스트 가져오기 */
    @Query("select d.department from Department d where d.party = :party")
    List<String> findDepartmentsNameByParty(@Param("party") Party party);

}
