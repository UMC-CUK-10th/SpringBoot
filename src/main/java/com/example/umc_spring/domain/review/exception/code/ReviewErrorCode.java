package com.example.umc_spring.domain.review.exception.code;

import com.example.umc_spring.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(6001, "리뷰를 찾을 수 없습니다.");

    private final Integer code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .code(code.toString())
                .message(message)
                .build();
    }
}
