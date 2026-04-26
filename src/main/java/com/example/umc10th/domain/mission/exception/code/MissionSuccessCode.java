package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록 조회에 성공했습니다."),
    MISSION_DETAIL_FOUND(HttpStatus.OK, "MISSION200_2", "미션 상세 조회에 성공했습니다."),
    MISSION_SUCCESS(HttpStatus.OK, "MISSION200_3", "미션 성공 요청이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}