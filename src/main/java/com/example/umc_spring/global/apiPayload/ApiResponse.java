package com.example.umc_spring.global.apiPayload;

import com.example.umc_spring.global.apiPayload.code.BaseCode;
import com.example.umc_spring.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring.global.apiPayload.code.GeneralSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final T result;

    public static <T> ApiResponse<T> onSuccess(T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(GeneralSuccessCode.OK.getReason().getCode())
                .message(GeneralSuccessCode.OK.getReason().getMessage())
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(code.getReason().getCode())
                .message(code.getReason().getMessage())
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .code(code.getReason().getCode())
                .message(code.getReason().getMessage())
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T result) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .result(result)
                .build();
    }
}