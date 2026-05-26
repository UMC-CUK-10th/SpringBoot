package com.example.springboot10th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();

    default ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(getMessage())
                .code(getCode())
                .isSuccess(true)
                .build();
    }

    default ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(getMessage())
                .code(getCode())
                .isSuccess(true)
                .httpStatus(getHttpStatus())
                .build();
    }
}