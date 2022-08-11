package com.example.pohab.Service;

import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.Entity.Party;
import com.example.pohab.Repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PartyService {
    @Autowired
    PartyRepository partyRepository;

    public Party enroll(PartyEnrollDTO partyEnrollDTO) {
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

        partyRepository.save(party);
        return party;
    }

    List<Party> findAll() {
        return partyRepository.findAll();
    }
}
