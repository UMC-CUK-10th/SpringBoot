package com.example.umc_spring.domain.mission.exception.code;

import com.example.umc_spring.global.apiPayload.code.BaseCode;
import com.example.umc_spring.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseCode {

    MISSION_OK(5000, "미션 조회 성공");

    private final Integer code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .code(code)
                .message(message)
                .build();
    }
}
