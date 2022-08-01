package com.example.pohab.Enum;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IsSubmit {
    @JsonProperty("임시 저장") temp,
    @JsonProperty("제출") submission,
    @JsonProperty("미제출") unsubmitted
}
