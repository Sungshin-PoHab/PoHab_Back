package com.example.pohab.Enum;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IsPass {
    @JsonProperty("발표 대기") waiting,
    @JsonProperty("합격") pass,
    @JsonProperty("불합격") fail
}
