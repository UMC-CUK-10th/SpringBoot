package com.example.umc_spring.domain.member.exception;

import com.example.umc_spring.domain.member.exception.code.MemberErrorCode;
import com.example.umc_spring.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(MemberErrorCode code) {
        super(code);
    }
}
