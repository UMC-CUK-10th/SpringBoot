package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 5주차 예제 - 마이페이지 API
    public record GetInfo(
            Long id
    ) {}

    // 회원가입 API
    public record SignUpDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password, // 추가된 속성
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생일은 필수입니다.")
            LocalDate birth,
            @NotNull(message = "주소는 필수입니다.")
            String address,
            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNum,
            List<Long> preferCategory
    ){}

    // 로그인 API
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
