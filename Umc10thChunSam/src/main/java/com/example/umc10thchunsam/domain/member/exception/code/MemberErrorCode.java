package com.example.umc10thchunsam.domain.member.exception.code;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {    // For test
    Member_NotFound(HttpStatus.BAD_REQUEST, "아이디 중복", "해당 유저 없음"),
    Member_Wrong(HttpStatus.BAD_REQUEST, "아이디 틀림", "해당 유저 없음"),

    NO_Garanted(HttpStatus.BAD_REQUEST, "TEST400_3", "권한 없음"),
    SAVE_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_", "유저미션 저장 실패연.."),
    Wrong_Passwd(HttpStatus.BAD_REQUEST, "TEST400_", "비밀번호가 틀렸습니다")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

