package com.example.pohab.Controller;

import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.DTO.PartyListForStaffDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping("/enroll")
    public Party enrollParty(@RequestBody PartyEnrollDTO partyEnrollDTO) {
        UserDetailsImpl userDetailsIml = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetailsIml.getId());
        return partyService.enroll(userDetailsIml.getId(), partyEnrollDTO);
    }

    /** 소속(party) 운영진(Staff)별로 읽기 */
    @GetMapping("/staff")
    public List<PartyListForStaffDto> partyByStaff() {
        UserDetailsImpl userDetailsIml = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return partyService.getPartyListForStaffDto(userDetailsIml);
    }

}
