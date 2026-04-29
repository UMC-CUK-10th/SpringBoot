package com.example.umc10th.domain.user.dto;

public class UserReqDTO {

    public record SignUp(
            String email,
            String password,
            String nickname
    ) {}

    public record UpdateUser(
            String nickname
    ) {}
}