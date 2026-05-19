package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "이미 가입된 이메일입니다."),

    MEMBER_INVALID(HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "유효하지 않은 자격 증명입니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),

    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_2",
            "해당 음식 카테고리를 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
