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

    // 모집 일정(step) 생성
    public List<Step> createStep(String party_id, ArrayList<CreateStepDto> createStepDto) {
        ArrayList<Step> steps = new ArrayList<>();

        Party party = this.partyRepository.findById(party_id).get();
        for (CreateStepDto stepDto : createStepDto) {
            Step step = new Step();
            step.setParty(party);
            step.setStep(stepDto.getStep());
            step.setEndDate(stepDto.getEndDate());
            steps.add(step);
        }

        return this.stepRepository.saveAll(steps);
    }

    // 모집 일정(step) 전체 읽어오기
    public List<Step> getAllStep() {
        return this.stepRepository.findAll();
    }

    // 모집 일정(step) 소속(party) 별로 읽어오기
    public List<Step> getPartyStep(String party_id) {
        return this.stepRepository.findAllByParty_Id(party_id);
    }

    // 모집 일정(step) 날짜 수정하기
    public Step updateStep(Integer step_id, UpdateStepDto updateStepDto) {
        Step update_step = this.stepRepository.findById(step_id).orElse(null);

        if (update_step != null) {
            update_step.setEndDate(updateStepDto.getEndDate());
            return this.stepRepository.save(update_step);
        } else {
            // 후에 exception 처리 필요
            return null;
        }
    }

    // 모집 일정(step) 삭제하기
    public void deleteStep(Integer step_id) {
        this.stepRepository.deleteById(step_id);
    }
}
