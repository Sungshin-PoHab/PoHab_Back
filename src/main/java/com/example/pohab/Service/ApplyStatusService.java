package com.example.pohab.Service;

import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.Party;

import java.util.List;

public interface ApplyStatusService {

    /** 지원 현황 전체 리스트 가져오기 */
    public List<ApplyStatus> getAllApplyStatus();

}
