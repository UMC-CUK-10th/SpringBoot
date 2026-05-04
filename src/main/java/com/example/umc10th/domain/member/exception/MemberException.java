package com.example.umc10th.domain.member.exception;


import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException {
    private final MemberErrorCode errorCode;
}
