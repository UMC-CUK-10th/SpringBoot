package com.example.umc10th.domain.reviews.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE_OK(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 등록되었습니다."),
    REVIEW_INFO_OK(HttpStatus.OK, "REVIEW200_1", "리뷰 작성 페이지 정보를 성공적으로 조회했습니다."),
    REVIEW_LIST_OK(HttpStatus.OK, "REVIEW200_2", "나의 리뷰 목록을 성공적으로 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
