package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final BaseErrorCode code;

    public MemberException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}