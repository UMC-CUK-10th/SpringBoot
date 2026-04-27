package com.example.umc10th.domain.members.dto;


import java.time.LocalDateTime;
import java.util.List;

public class MemberReqDTO {

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

    public record TermAgreementDTO(
            Long termsId,
            Boolean isAgreed
    ) {}
}