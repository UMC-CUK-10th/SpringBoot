package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @AllArgsConstructor
    public static class SignUpResultDTO {
        private Long memberId;
        private String email;
        private String name;
    }
}
