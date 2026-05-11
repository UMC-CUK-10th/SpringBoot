package com.example.umc10th.domain.member.dto;

public class MemberResDTO {

    public static class SignUpResultDTO {

        private Long memberId;
        private String email;
        private String nickname;

        public SignUpResultDTO(Long memberId, String email, String nickname) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
        }

        public Long getMemberId() {
            return memberId;
        }

        public String getEmail() {
            return email;
        }

        public String getNickname() {
            return nickname;
        }
    }

    public static class LoginResultDTO {

        private Long memberId;
        private String email;
        private String nickname;
        private String accessToken;

        public LoginResultDTO(Long memberId, String email, String nickname, String accessToken) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
            this.accessToken = accessToken;
        }

        public Long getMemberId() {
            return memberId;
        }

        public String getEmail() {
            return email;
        }

        public String getNickname() {
            return nickname;
        }

        public String getAccessToken() {
            return accessToken;
        }
    }

    public static class MyPageResponseDTO {

        private Long memberId;
        private String email;
        private String nickname;
        private String phoneNumber;
        private Boolean isPhoneVerified;
        private Integer point;

        public MyPageResponseDTO(
                Long memberId,
                String email,
                String nickname,
                String phoneNumber,
                Boolean isPhoneVerified,
                Integer point
        ) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
            this.phoneNumber = phoneNumber;
            this.isPhoneVerified = isPhoneVerified;
            this.point = point;
        }

        public Long getMemberId() {
            return memberId;
        }

        public String getEmail() {
            return email;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Boolean getIsPhoneVerified() {
            return isPhoneVerified;
        }

        public Integer getPoint() {
            return point;
        }
    }
}