package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class SignUpDTO {
        private String email;
        private String password;
        private String name;
    }
}