package com.example.umc10th.domain.member.exception;


import com.example.umc10th.global.apiPayload.code.BaseErrorCode;




 // <--- 이놈이 모든 필드를 받는 생성자를 만들어준다!
public class MemberException extends RuntimeException {

    private final BaseErrorCode code; // 에러 코드를 저장할 필드
    public MemberException(BaseErrorCode code) {
        this.code = code;
    }
    // 만약 어노테이션이 안 먹히면 수동으로라도 이렇게 써라!
    /*
    public MemberException(BaseErrorCode code) {
        this.code = code;
    }
    */
}