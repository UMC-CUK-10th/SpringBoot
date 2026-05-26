package com.example.springboot10th.global.exception;

import com.example.springboot10th.global.apiPayload.code.BaseErrorCode;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
