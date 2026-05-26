package com.example.springboot10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게가 존재하지 않습니다."),
            ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}