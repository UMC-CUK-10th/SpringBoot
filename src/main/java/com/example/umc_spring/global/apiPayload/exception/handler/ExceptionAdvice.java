package com.example.umc_spring.global.apiPayload.exception.handler;

import com.example.umc_spring.global.apiPayload.ApiResponse;
import com.example.umc_spring.global.apiPayload.code.ErrorReasonDTO;
import com.example.umc_spring.global.apiPayload.code.GeneralErrorCode;
import com.example.umc_spring.global.apiPayload.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    // 프로젝트에서 발생한 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(GeneralException e) {

        ErrorReasonDTO reason = e.getErrorCode().getReason();

        return ResponseEntity
                .status(reason.getStatus())
                .body(ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null));
    }

    // 그 외에 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {

        ErrorReasonDTO reason = GeneralErrorCode.INTERNAL_SERVER_ERROR.getReason();

        return ResponseEntity
                .status(reason.getStatus())
                .body(ApiResponse.onFailure(reason.getCode(), reason.getMessage(), ex.getMessage()));
    }
}
