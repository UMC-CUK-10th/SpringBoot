package com.example.umc10th.domain.stores.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다."),
    REGIONS_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REGIONS404_1",
            "해당 지역을 찾을 수 없습니다."),
    MISSIONS_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
}
