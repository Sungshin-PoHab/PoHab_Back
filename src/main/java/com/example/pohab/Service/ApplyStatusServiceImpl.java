package com.example.pohab.Service;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Repository.ApplyStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyStatusServiceImpl implements ApplyStatusService {

    private final ApplyStatusRepository applyStatusRepository;

    @Override
    public List<ApplyStatus> getAllApplyStatus() {
        return applyStatusRepository.findAll();
    }

}
