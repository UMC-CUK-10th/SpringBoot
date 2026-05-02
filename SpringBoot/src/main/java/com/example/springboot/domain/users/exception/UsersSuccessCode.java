package com.example.springboot.domain.users.exception;

import com.example.springboot.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UsersSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MEMVER200_1",
            "성공적으로 유저를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
