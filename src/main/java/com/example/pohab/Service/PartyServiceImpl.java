package com.example.pohab.Service;

import com.example.pohab.Entity.Party;
import com.example.pohab.Repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;

    @Override
    public List<Party> getAllParty() {
        List<Party> partyList = partyRepository.findAll();
        return partyList;
    }





}
