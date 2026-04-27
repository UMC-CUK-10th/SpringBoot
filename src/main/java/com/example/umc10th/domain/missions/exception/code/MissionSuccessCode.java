package com.example.umc10th.domain.missions.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_CREATED_OK(HttpStatus.OK, "MISSION200_1", "미션이 성공적으로 완료되었습니다."),
    MISSION_OK(HttpStatus.OK, "MISSION200_2", "미션이 성공적으로 조회되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}