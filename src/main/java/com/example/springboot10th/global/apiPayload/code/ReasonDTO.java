package com.example.springboot10th.global.apiPayload.code;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReasonDTO {
    private final boolean isSuccess;
    private final String code;
    private final String message;
}
