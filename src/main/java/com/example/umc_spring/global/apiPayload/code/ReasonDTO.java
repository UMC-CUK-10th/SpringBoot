package com.example.umc_spring.global.apiPayload.code;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReasonDTO {
    private Integer code;
    private String message;
}