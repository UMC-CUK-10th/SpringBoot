package com.example.springboot10th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    ReasonDTO getReason();
    ReasonDTO getReasonHttpStatus();
}
