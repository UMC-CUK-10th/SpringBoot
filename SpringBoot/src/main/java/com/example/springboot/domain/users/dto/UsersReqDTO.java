package com.example.springboot.domain.users.dto;

public class UsersReqDTO {

    // 마이페이지 (이전 기능 유지)
    public record GetInfo(
            Long id
    ) {}

    // 5. 회원가입 (Validation 적용)
    public record JoinDTO(
            @jakarta.validation.constraints.NotBlank
            String user_name,
            @jakarta.validation.constraints.NotBlank
            String nickname,
            @jakarta.validation.constraints.NotBlank
            String user_phone_number,
            @jakarta.validation.constraints.Email
            String email,
            @jakarta.validation.constraints.NotBlank
            String user_password
    ) {}

    // 진행 중인 미션 조회
    public record OngoingMissionReqDTO(
            @jakarta.validation.constraints.NotNull
            Long userId,
            Integer page
    ) {}
}
