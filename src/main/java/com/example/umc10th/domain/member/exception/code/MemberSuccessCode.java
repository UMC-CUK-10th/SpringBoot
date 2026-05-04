package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입에 성공했습니다."),
    HOME_FOUND(HttpStatus.OK, "MEMBER200_1", "홈 화면 조회에 성공했습니다."),
    POINT_FOUND(HttpStatus.OK, "MEMBER200_2", "포인트 내역 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}