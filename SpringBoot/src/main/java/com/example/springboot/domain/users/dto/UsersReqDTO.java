package com.example.springboot.domain.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsersReqDTO {

    // 마이페이지 (이전 기능 유지)
    public record GetInfo(
            Long id
    ) {}

    // 5. 회원가입 (Validation 적용)
    public record JoinDTO(

            @NotBlank
            String userName,

            @NotBlank
            String nickname,

            @NotBlank
            String userPhoneNumber,

            @Email
            String email,

            @NotBlank
            String userPassword
    ) {}

    // 진행 중인 미션 조회
    public record OngoingMissionReqDTO(
            @jakarta.validation.constraints.NotNull
            Long userId,
            Integer page
    ) {}
}
