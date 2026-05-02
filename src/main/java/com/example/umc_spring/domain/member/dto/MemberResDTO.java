package com.example.umc_spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberResDTO {

    @Getter
    @Builder
    public static class SignUpResultDTO {
        private Long memberId;
        private String userName;
        private String email;
        private String message;
    }

    @Getter
    @Builder
    public static class HomeInfoDTO {
        private String userName;
        private String userAddress;
        private Integer availableMissionCount;
        private List<String> favoriteFoods;
    }
}