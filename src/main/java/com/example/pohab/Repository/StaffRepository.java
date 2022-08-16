package com.example.pohab.Repository;

import com.example.pohab.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findStaffByUser_id(Integer user_id);
    List<Staff> findAllByParty_id(String party_id);
    Staff findByUser_id(Integer user_id);
    Staff findByUser_idAndParty_id(Integer user_id, String party_id);
}
