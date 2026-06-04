package com.example.umc_spring.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class JoinDTO {

        @NotBlank
        private String name;

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        private String gender;

        private Integer birth;

        private String address;
    }

    @Getter
    public static class LoginDTO {

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }
}