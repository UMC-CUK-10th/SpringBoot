package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.domain.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED_OK(HttpStatus.OK, "REVIEW200_1", "리뷰가 성공적으로 작성되었습니다."),
    REVIEW_OK(HttpStatus.OK, "REVIEW200_2", "리뷰가 성공적으로 조회되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
