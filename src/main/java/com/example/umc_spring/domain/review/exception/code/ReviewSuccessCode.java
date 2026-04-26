package com.example.umc_spring.domain.review.exception.code;

import com.example.umc_spring.global.apiPayload.code.BaseCode;
import com.example.umc_spring.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseCode {

    REVIEW_OK(6000, "리뷰 조회 성공");

    private final Integer code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .code(code.toString())
                .message(message)
                .build();
    }
}
