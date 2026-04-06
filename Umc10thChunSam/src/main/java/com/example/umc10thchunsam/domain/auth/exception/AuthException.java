package com.example.umc10thchunsam.domain.auth.exception;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thchunsam.global.apiPayload.exception.GeneralException;

public class AuthException extends GeneralException {
    public AuthException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
