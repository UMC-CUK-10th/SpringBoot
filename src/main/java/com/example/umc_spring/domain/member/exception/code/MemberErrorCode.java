package com.example.umc_spring.domain.member.exception.code;

import com.example.umc_spring.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(4001, "사용자를 찾을 수 없습니다.");

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
