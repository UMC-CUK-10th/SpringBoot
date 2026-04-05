package com.example.umc_spring.global.apiPayload.exception.handler;

import com.example.umc_spring.global.apiPayload.ApiResponse;
import com.example.umc_spring.global.apiPayload.code.ErrorReasonDTO;
import com.example.umc_spring.global.apiPayload.exception.GeneralException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ApiResponse<Object> handleGeneralException(GeneralException e) {
        ErrorReasonDTO reason = e.getCode().getReason();
        return ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);
    }
}
