package com.example.pohab.Controller;

import com.example.pohab.DTO.PartyEnrollDTO;
import com.example.pohab.Entity.Party;
import com.example.pohab.Repository.PartyRepository;
import com.example.pohab.Service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping("/enroll")
    public Party enrollParty(@RequestHeader("Authorization") String code, @RequestBody PartyEnrollDTO partyEnrollDTO) {
        System.out.println(code);
        return partyService.enroll(partyEnrollDTO);
    }
}
