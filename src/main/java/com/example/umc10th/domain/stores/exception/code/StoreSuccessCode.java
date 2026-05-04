package com.example.umc10th.domain.stores.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    STORE_LIST_OK(HttpStatus.OK, "STORE200_1", "가게 목록을 성공적으로 조회했습니다."),
    STORE_DETAIL_OK(HttpStatus.OK, "STORE200_2", "가게 상세 정보를 성공적으로 조회했습니다."),
    REVIEW_LIST_OK(HttpStatus.OK, "STORE200_3", "리뷰 목록을 성공적으로 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
