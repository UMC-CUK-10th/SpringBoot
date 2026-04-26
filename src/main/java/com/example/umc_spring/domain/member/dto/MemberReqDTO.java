package com.example.umc_spring.domain.member.dto;

import lombok.Getter;

import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class SignUpDTO {
        private Boolean termsAgreed;
        private Boolean privacyAgreed;
        private String userName;
        private String gender;
        private Integer userBirth;
        private String userAddress;
        private String email;
        private String phoneNumber;
        private List<Long> favoriteFoodIds;
    }
}