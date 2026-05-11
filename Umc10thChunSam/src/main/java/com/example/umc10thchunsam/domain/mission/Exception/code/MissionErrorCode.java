package com.example.umc10thchunsam.domain.mission.Exception.code;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    // For test
    No_Mem_EXCEPTION(HttpStatus.BAD_REQUEST, "TES2", "맴버 없음"),
    No_Mission_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_3", "가게 없음"),
    SAVE_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_", "유저미션 저장 실패연.."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
