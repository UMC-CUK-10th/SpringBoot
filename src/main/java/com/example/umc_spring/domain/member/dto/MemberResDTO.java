package com.example.umc_spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Getter
    @Builder
    public static class MyPageDTO {
        private Long userId;
        private String userName;
        private Integer userPoint;
        private Long reviewCount;
        private Long inProgressMissionCount;
        private Long completedMissionCount;
    }

    @Getter
    @Builder
    public static class JoinResultDTO {
        private Long memberId;
        private LocalDateTime createdAt;
    }
}