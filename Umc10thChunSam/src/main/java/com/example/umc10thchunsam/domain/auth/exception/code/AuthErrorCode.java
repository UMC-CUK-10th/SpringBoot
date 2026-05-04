package com.example.umc10thchunsam.domain.auth.exception.code;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {    // For test
    ID_Exists(HttpStatus.BAD_REQUEST, "아이디 중복", "중복된 아이디 입니다."),
    No_Mission_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_3", "가게 없음"),
    SAVE_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_", "유저미션 저장 실패"),
    Wrong_Passwd_ID(HttpStatus.BAD_REQUEST, "TEST400_", "올바르지 않은 ID,비번"),
    No_Exists_ID(HttpStatus.BAD_REQUEST, "TEST400_", "사용자를 찾을 수 없습니다.")

            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

