package com.example.pohab.Controller;

import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.Entity.Party;
import com.example.pohab.Repository.PartyRepository;
import com.example.pohab.Service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping("/enroll")
    public Party enrollParty(@RequestBody PartyEnrollDTO partyEnrollDTO) {
        return partyService.enroll(partyEnrollDTO);
    }
}
