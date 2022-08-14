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
import java.util.List;

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
                staff.setUser(user);

                return this.staffRepository.save(staff);
            } else {
                throw new StaffCodeErrorException("잘못된 코드입니다.");
            }
        }
    }

    // 운영진(Staff) 소속(Party)별로 읽기
    public List<Staff> getStaffByParty(String party_id) {
        return this.staffRepository.findAllByParty_id(party_id);
    }

    // 운영진(Staff) 삭제하기
    public void deleteStaff(Integer staff_id) {
        this.staffRepository.deleteById(staff_id);
    }

    // 운영진(Staff) userId별로 가져오기
    public List<Staff> findAllByUser(int userId) {
         return staffRepository.findAllByUser_Id(userId);
    }
}
