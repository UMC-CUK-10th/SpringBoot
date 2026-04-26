package com.example.umc_spring.domain.mission.exception.code;

import com.example.umc_spring.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(5001, "미션을 찾을 수 없습니다.");

    private final Integer code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .code(code.toString())
                .message(message)
                .build();
    }
}
