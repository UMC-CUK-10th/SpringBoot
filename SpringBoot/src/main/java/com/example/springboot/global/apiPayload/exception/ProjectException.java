package com.example.springboot.global.apiPayload.exception;

import com.example.springboot.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public ProjectException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
