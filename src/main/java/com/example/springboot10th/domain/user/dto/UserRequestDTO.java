package com.example.springboot10th.domain.user.dto;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class UpdateProfileRequest {
        private String name;
        private String nickname;
        private String gender;
    }
}
