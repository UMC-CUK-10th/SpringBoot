package com.example.umc_spring.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class JoinDTO {

        private String name;

        private String email;

        private String password;

        private String gender;

        private Integer birth;

        private String address;
    }
}