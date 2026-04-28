package com.example.springboot.domain.users.dto;

public class UsersReqDTO {

    // 마이페이지 (이전 기능 유지)
    public record GetInfo(
            Long id
    ) {}

    // 5. 회원가입
    public record JoinDTO(
            String user_name,
            String nickname,
            String user_phone_number,
            String email,
            String user_password
    ) {}
}
