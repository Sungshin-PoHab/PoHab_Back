package com.example.pohab.Service;

import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Staff;
import com.example.pohab.Entity.User;
import com.example.pohab.Repository.PartyRepository;
import com.example.pohab.Repository.StaffRepository;
import com.example.pohab.Repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PartyService {

    private PartyRepository partyRepository;
    private StaffRepository staffRepository;
    private UserRepository userRepository;

    @Autowired
    public PartyService (PartyRepository partyRepository, StaffRepository staffRepository, UserRepository userRepository){
        this.partyRepository = partyRepository;
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
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
                    .role("운영진")
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
}
