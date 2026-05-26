package com.example.springboot10th.global.exception;

import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.ErrorReasonDTO;
import com.example.springboot10th.global.apiPayload.code.GeneralErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GeneralExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = Optional.ofNullable(error.getDefaultMessage()).orElse("");
            errors.merge(fieldName, errorMessage, (existing, newMsg) -> existing + ", " + newMsg);
        });

        ErrorReasonDTO errorReason = GeneralErrorCode.BAD_REQUEST.getReasonHttpStatus();
        ApiResponse<Map<String, String>> body = ApiResponse.onFailure(errorReason.getCode(), errorReason.getMessage(), errors);

        return super.handleExceptionInternal(
                ex,
                body,
                headers,
                errorReason.getHttpStatus(),
                request);
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<Object> handleProjectException(ProjectException e, WebRequest request) {
        ErrorReasonDTO errorReasonHttpStatus = e.getErrorReasonHttpStatus();
        return handleExceptionInternal(e, errorReasonHttpStatus, null, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        e.printStackTrace();
        ErrorReasonDTO errorReason = GeneralErrorCode.INTERNAL_SERVER_ERROR.getReasonHttpStatus();
        return handleExceptionInternal(e, errorReason, HttpHeaders.EMPTY, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDTO reason,
            HttpHeaders headers, WebRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                request);
    }
}
