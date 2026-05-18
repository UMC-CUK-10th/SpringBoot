package com.example.umc10th.domain.members.dto;


import com.example.umc10th.domain.members.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record SignUpDTO(
            String social_type,
            String providerId,
            String nickname,

            @NotBlank(message = "이메일은 필수 입력 항목입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
            String password,

            List<TermAgreementDTO> terms,

            @NotBlank(message = "이름은 필수 입력 항목입니다.")
            String name,

            Gender gender,
            LocalDate birth,
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