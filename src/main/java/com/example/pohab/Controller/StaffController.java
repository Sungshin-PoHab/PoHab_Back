package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateStaffDto;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Repository.StaffRepository;
import com.example.pohab.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // 운영진(Staff) 생성
    @PostMapping("/{party_id}")
    public Staff createStaff(@PathVariable("party_id") String party_id,
                             @RequestBody() CreateStaffDto createStaffDto) {
        return this.staffService.createStaff(party_id, createStaffDto);
    }

    // 운영진(Staff) 소속(Party)별로 읽기
    @GetMapping("/{party_id}")
    public List<Staff> getStaffByParty(@PathVariable("party_id") String party_id) {
        return this.staffService.getStaffByParty(party_id);
    }
}
