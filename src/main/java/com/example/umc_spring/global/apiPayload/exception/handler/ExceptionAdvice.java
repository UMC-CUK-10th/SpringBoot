package com.example.umc_spring.global.apiPayload.exception.handler;

import com.example.umc_spring.global.apiPayload.ApiResponse;
import com.example.umc_spring.global.apiPayload.code.ErrorReasonDTO;
import com.example.umc_spring.global.apiPayload.code.GeneralErrorCode;
import com.example.umc_spring.global.apiPayload.exception.GeneralException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

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

    // @RequestBody @Valid 검증 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ErrorReasonDTO reason = GeneralErrorCode.BAD_REQUEST.getReason();

        return ResponseEntity
                .status(reason.getStatus())
                .body(ApiResponse.onFailure(reason.getCode(), reason.getMessage(), errors));
    }

    // @RequestParam, @PathVariable 검증 실패 처리
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException e
    ) {
        Map<String, String> errors = new HashMap<>();

        e.getConstraintViolations().forEach(error -> {
            errors.put(error.getPropertyPath().toString(), error.getMessage());
        });

        ErrorReasonDTO reason = GeneralErrorCode.BAD_REQUEST.getReason();

        return ResponseEntity
                .status(reason.getStatus())
                .body(ApiResponse.onFailure(reason.getCode(), reason.getMessage(), errors));
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