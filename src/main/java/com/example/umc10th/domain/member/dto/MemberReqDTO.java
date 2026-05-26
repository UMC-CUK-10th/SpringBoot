package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(Long id) {
    }

    // 회원가입
    @Getter
    @NoArgsConstructor
    public static class JoinDTO {

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생년월일은 필수입니다.")
        private LocalDate birth;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        private String detailAddress;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        private String password;

        // 선호 음식 ID 목록 (중복 선택 가능)
        private List<Long> preferredFoodIds;

        // 동의한 약관 ID 목록
        private List<Long> agreedTermIds;
    }
}