package com.example.umc10th.domain.members.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class MemberReqDTO {

    // 구현 전
    public record SignUpDTO(
            String social_type,
            String providerId,
            List<TermAgreementDTO> terms,
            String name,
            String gender,
            String birth,
            String address,
            List<Long> preferences,
            LocalDateTime completedAt,
            LocalDateTime updatedAt
    ) {}

    // 구현 전
    public record TermAgreementDTO(
            Long termsId,
            Boolean isAgreed
    ) {}
}