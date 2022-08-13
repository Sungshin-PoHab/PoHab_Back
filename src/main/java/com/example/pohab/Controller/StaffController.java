package com.example.pohab.Controller;

import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Login.Service.KakaoUserService;
import com.example.pohab.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StaffController {

    private final StaffService staffService;
    private final KakaoUserService userService;

    /** 운영진별 소속(party) 가져오기 */
    @GetMapping("/party/forStaff")
    public List<Staff> getPartyByStaff() {
        UserDetailsImpl userDetailsIml = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetailsIml.getEmail());
        User user = userService.findUserByEmail(userDetailsIml.getEmail());
        return staffService.getAllByUser(user);
    }

}
