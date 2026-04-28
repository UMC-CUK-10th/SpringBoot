package com.example.springboot.domain.users.exception;

import com.example.springboot.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UsersErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "존재하지 않는 사용자입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
