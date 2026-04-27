package com.example.umc10th.domain.members.dto;

import java.time.LocalDateTime;

public class MemberResDTO {
    public record SignUpResultDTO(
            Long memberId,
            LocalDateTime createAt
    ) {}
}

