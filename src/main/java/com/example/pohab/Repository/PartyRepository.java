package com.example.pohab.Repository;

import com.example.pohab.Entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, String> {

//    /** 소속 정보 + 지원자 수 */
//    @Query("select count(*) from ApplyStatus a " +
//            "left join Department d on a.department.id = d.id " +
//            "where a.department =: department")
//    List<Object[]> getPartyWithApplicants(@Param("id") Long  id);

}
