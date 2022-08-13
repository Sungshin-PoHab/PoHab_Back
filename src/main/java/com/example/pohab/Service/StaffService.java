package com.example.pohab.Service;

import com.example.pohab.DTO.CreateStaffDto;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import com.example.pohab.Exception.StaffCodeErrorException;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Repository.PartyRepository;
import com.example.pohab.Repository.StaffRepository;
import com.example.pohab.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    // 운영진(Staff) 생성
    public Staff createStaff(String party_id, CreateStaffDto createStaffDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer user_id = userDetails.getId();

        Party party = this.partyRepository.findById(party_id).orElse(null);
        User user = this.userRepository.findById(user_id).orElse(null);

        if (party == null || user == null) {
            // 추후 에러처리 필요
            System.out.println("error");
            return null;
        } else {
            if (party.getCode().equals(createStaffDto.getCode())) {
                Staff staff = new Staff();
                staff.setRole(createStaffDto.getRole());
                staff.setParty(party);

                return this.staffRepository.save(staff);
            } else {
                throw new StaffCodeErrorException("잘못된 코드입니다.");
            }
        }
    }
}
