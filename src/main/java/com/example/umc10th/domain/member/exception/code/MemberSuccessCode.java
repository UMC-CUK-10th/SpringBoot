package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_SIGNUP_OK(HttpStatus.OK,
            "MEMBER200_1",
            "회원가입이 성공적으로 완료했습니다."),

    MEMBER_FOUND_OK(HttpStatus.OK,
            "MEMBER200_2",
            "사용자를 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
