package com.example.springboot10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.OK,
            "MISSION_200_1",
            "성공적으로 미션을 생성했습니다."),
    OK(HttpStatus.OK,
            "MISSION_200_2",
            "성공적으로 미션을 조회했습니다."),
            ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
