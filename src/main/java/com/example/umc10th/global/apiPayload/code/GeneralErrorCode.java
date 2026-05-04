package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    // 400 Bad Request: 잘못된 요청 (형식 오류, 필수값 누락 등)
    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),

    // 401 Unauthorized: 인증 필요 혹은 토큰 무효
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "AUTH401_1",
            "인증이 필요합니다."),

    // 403 Forbidden: 권한 부족으로 접근 거부
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "AUTH403_1",
            "요청이 거부되었습니다."),

    // 404 Not Found: 리소스를 찾을 수 없음
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "요청한 리소스를 찾을 수 없습니다."),

    // 500 Internal Server Error: 예기치 않은 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500_1",
            "예기치 않은 서버 에러가 발생했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
