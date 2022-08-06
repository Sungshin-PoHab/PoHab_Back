package com.example.pohab.Service;

import com.example.pohab.Entity.Step;
import com.example.pohab.Repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;

    /** 단계 전체 리스트 가져오기 */
    public List<Step> getAllStep() {
        return stepRepository.findAll();
    }

    /** 기본키로 특정 단계 가져오기 */
    public Step getStepById(int id) {
        return stepRepository.findById(id).get();
    }

}