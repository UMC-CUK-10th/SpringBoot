package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MemberReqDTO {

    // 5주차 예제 - 마이페이지 API
    public record GetInfo(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long id
    ) {}

    // 회원가입 API
    public record SignUpDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생일은 필수입니다.")
            LocalDate birth,
            @NotBlank(message = "주소는 필수입니다.")
            String address,
            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNum
    ){}

    // 로그인 API
    public record LoginDTO(
            @NotBlank(message = "이메일은 필수입니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ){}
}
