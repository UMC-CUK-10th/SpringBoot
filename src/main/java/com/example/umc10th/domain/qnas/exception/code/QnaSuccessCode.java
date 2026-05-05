package com.example.umc10th.domain.qnas.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QnaSuccessCode implements BaseSuccessCode { // 인터페이스 구현 필수
    QNA_CREATE_OK(HttpStatus.CREATED, "QNA201_1", "문의가 성공적으로 등록되었습니다."),
    QNA_LIST_OK(HttpStatus.OK, "QNA200_1", "나의 문의 내역을 성공적으로 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}