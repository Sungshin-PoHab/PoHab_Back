package com.example.pohab.Service;

import com.example.pohab.DTO.CreateStepDto;
import com.example.pohab.DTO.UpdateStepDto;
import com.example.pohab.Entity.Party;
import com.example.pohab.Entity.Step;
import com.example.pohab.Repository.PartyRepository;
import com.example.pohab.Repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitService {
    private StepRepository stepRepository;
    private PartyRepository partyRepository;

    @Autowired
    public RecruitService(StepRepository stepRepository, PartyRepository partyRepository) {
        this.stepRepository = stepRepository;
        this.partyRepository = partyRepository;

    }

    public List<Step> createStep(String party_id, ArrayList<CreateStepDto> createStepDto) {
        ArrayList<Step> steps = new ArrayList<>();

        Party party = this.partyRepository.findById(party_id).get();
        for (CreateStepDto stepDto : createStepDto) {
            Step step = new Step();
            step.setParty(party);
            step.setStep(stepDto.getStep());
            step.setEnd_date(stepDto.getEnd_date());
            steps.add(step);
        }

        return this.stepRepository.saveAll(steps);
    }

    public List<Step> getAllStep() {
        return this.stepRepository.findAll();
    }

    public List<Step> getPartyStep(String party_id) {
        return this.stepRepository.findAllByParty_Id(party_id);
    }

    public Step updateStep(Integer step_id, UpdateStepDto updateStepDto) {
        Step update_step = this.stepRepository.findById(step_id).orElse(null);

        if (update_step != null) {
            update_step.setEnd_date(updateStepDto.getEnd_date());
            return this.stepRepository.save(update_step);
        } else {
            // 후에 exception 처리 필요
            return null;
        }
    }
}
