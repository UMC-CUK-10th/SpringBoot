package com.example.springboot.domain.users.exception;

import com.example.springboot.global.apiPayload.code.BaseErrorCode;
import com.example.springboot.global.apiPayload.exception.ProjectException;

public class UsersException extends ProjectException {
    public UsersException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
