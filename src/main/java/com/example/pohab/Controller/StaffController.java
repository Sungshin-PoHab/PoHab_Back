package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateStaffDto;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Login.Service.KakaoUserService;
import com.example.pohab.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StaffController {

    private StaffService staffService;
    private KakaoUserService userService;

    @Autowired
    public StaffController(StaffService staffService, KakaoUserService userService) {
        this.staffService = staffService;
        this.userService = userService;
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

    // 운영진(Staff) 삭제하기
    @DeleteMapping("/{staff_id}")
    public void deleteStaff(@PathVariable("staff_id") Integer staff_id) {
        this.staffService.deleteStaff(staff_id);
    }

    /** 운영진별 소속(party) 가져오기 */
    @GetMapping("/party/forStaff")
    public List<Staff> getPartyByStaff() {
        UserDetailsImpl userDetailsIml = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetailsIml.getEmail());
        User user = userService.findUserByEmail(userDetailsIml.getEmail());
        return staffService.getAllByUser(user);
    }

}
