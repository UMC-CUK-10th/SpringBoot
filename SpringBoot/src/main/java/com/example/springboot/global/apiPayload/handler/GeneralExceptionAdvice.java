package com.example.springboot.global.apiPayload.handler;

import com.example.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.global.apiPayload.code.BaseErrorCode;
import com.example.springboot.global.apiPayload.code.GeneralErrorCode;
import com.example.springboot.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 프로젝트에서 발생한 예외 처리
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberException(
            ProjectException e
    ) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }

    // Bean Validation 예외 처리
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<java.util.Map<String, String>>> handleMethodArgumentNotValidException(
            org.springframework.web.bind.MethodArgumentNotValidException ex
    ) {
        java.util.Map<String, String> errors = new java.util.HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                errors
                        )
                );
    }
}