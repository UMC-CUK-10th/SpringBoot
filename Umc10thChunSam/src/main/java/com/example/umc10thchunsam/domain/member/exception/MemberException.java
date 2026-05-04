package com.example.umc10thchunsam.domain.member.exception;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thchunsam.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }

}
