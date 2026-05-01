package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.domain.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_OK(HttpStatus.OK, "MEMBER200_1", "회원가입이 완료되었습니다."),
    LOGIN_OK(HttpStatus.OK, "MEMBER200_2", "로그인이 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}