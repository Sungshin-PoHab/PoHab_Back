package com.example.pohab.Repository;

import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    public List<Staff> findAllByParty_id(String party_id);
    public List<Staff> findAllByUser_Id(int user_id);
}
