package com.example.umc_spring.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseCode {

    OK(HttpStatus.OK, "COMMON200_1", "요청에 성공했습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201_1", "요청에 성공하여 리소스가 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .status(status)
                .code(code)
                .message(message)
                .build();
    }
}