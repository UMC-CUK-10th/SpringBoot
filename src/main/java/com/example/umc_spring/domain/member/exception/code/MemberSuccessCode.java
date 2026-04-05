package com.example.umc_spring.domain.member.exception.code;

import com.example.umc_spring.global.apiPayload.code.BaseCode;
import com.example.umc_spring.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseCode {

    MEMBER_OK(2000, "사용자 조회 성공");

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