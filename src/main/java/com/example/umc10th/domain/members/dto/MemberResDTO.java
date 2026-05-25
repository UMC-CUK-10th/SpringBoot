package com.example.umc10th.domain.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    // 마이페이지
    public static class MyPageDTO {
        String nickname;
        String email;
        String phoneNumber;
        Integer totalPoint;
    }

    // 로그인 응답 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDTO {
        Long memberId;
        String accessToken;
    }
}

