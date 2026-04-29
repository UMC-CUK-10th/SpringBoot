package com.example.umc10th.domain.user.dto;

public class UserResDTO {

    public record UserInfo(
            String email,
            String nickname,
            int point
    ) {}
}