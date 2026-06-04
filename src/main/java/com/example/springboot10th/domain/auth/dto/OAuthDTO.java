package com.example.springboot10th.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;




public class OAuthDTO {

    @Getter
    @Builder
    public static class OAuthLoginResponse {
        private String accessToken;
        private boolean isNewUser;
        private String nickname;
        private String email;
    }
}
