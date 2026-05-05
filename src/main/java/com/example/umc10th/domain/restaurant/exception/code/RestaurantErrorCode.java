package com.example.umc10th.domain.restaurant.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RestaurantErrorCode implements BaseErrorCode {

    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND,
            "RESTAURANT404_1",
            "해당 식당을 찾을 수 없습니다."),

    RESTAURANT_DUPLICATED(HttpStatus.CONFLICT,
            "REST409_1",
            "해당 지역에 이미 동일한 상호가 존재합니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
