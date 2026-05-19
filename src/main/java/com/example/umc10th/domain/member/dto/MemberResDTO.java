package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    // 5주차 예제 - 마이페이지 API
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    // 회원가입 API
    @Builder
    public record SignUpDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    // 로그인 API
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
