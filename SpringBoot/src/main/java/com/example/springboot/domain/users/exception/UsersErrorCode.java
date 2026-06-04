package com.example.springboot.domain.users.exception;

import com.example.springboot.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UsersErrorCode implements BaseErrorCode {

    USERS_NOT_FOUND(HttpStatus.NOT_FOUND, "USERS404_1", "존재하지 않는 사용자입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "USERS400_2", "이미 가입된 이메일입니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION404_1", "존재하지 않는 지역입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 미션입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USERS400_3", "비밀번호가 일치하지 않습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "USERS400_4", "지원하지 않는 소셜 로그인 제공자입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
