package com.example.pohab.Service;

import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.DTO.PartyListForStaffDto;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Random;

@Service
public class PartyService {

    private PartyRepository partyRepository;
    private StaffRepository staffRepository;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private StepRepository stepRepository;

    @Autowired
    public PartyService (PartyRepository partyRepository, StaffRepository staffRepository, UserRepository userRepository, DepartmentRepository departmentRepository, StepRepository stepRepository){
        this.partyRepository = partyRepository;
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.stepRepository = stepRepository;
    }

    public Party enroll(Integer userId, PartyEnrollDTO partyEnrollDTO) {
        Party party = new Party();

        String nth = partyEnrollDTO.getNth().toString();
        String partyID = partyEnrollDTO.getName() + '-' + nth;

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        String code = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        party.setId(partyID);
        party.setCategory(partyEnrollDTO.getCategory());
        party.setCode(code);
        party.setName(partyEnrollDTO.getName());

        //해당 소속에 user 운영진으로 등록
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User realuser = user.get();

            Staff staff = Staff.builder()
                    .party(party)
                    .role("회장")
                    .user(realuser)
                    .build();

            //소속 생성
            partyRepository.save(party);
            //소속 운영진 등록
            staffRepository.save(staff);

        } else {

        }
        return party;
    }

    List<Party> findAll() {
        return partyRepository.findAll();
    }

    /** staff & department & step -> PartyListForStaffDto */
    public List<PartyListForStaffDto> getPartyListForStaffDto(UserDetailsImpl userDetailsIml) {
        List<PartyListForStaffDto> partyDtos = new ArrayList<>();
        List<Staff> staffList = staffRepository.findAllByUser_Id(userDetailsIml.getId());

        for (Staff staff : staffList) {
            int deId = departmentRepository.findDepartmentByParty_IdAndDepartment(staff.getParty().getId(), "공통").getId();
            int step = stepRepository.findAllByParty(staff.getParty()).get(0).getId();
            PartyListForStaffDto dto = PartyListForStaffDto.builder()
                    .partyId(staff.getParty().getId())
                    .role(staff.getRole())
                    .departmentId(deId)
                    .stepId(step)
                    .build();
            partyDtos.add(dto);
        }
        return partyDtos;
    }
}
