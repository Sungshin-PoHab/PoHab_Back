package com.example.pohab.Repository;

import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> getAllByUser(User user);
    List<Staff> findAllByUser_Id(int user_id);
    Staff findStaffByUser_id(Integer user_id);
    List<Staff> findAllByParty_id(String party_id);
    Staff findByUser_id(Integer user_id);
    Staff findByUser_idAndParty_id(Integer user_id, String party_id);
}
