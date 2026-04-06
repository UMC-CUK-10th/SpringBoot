package com.example.umc10thchunsam.domain.auth.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class LoginResjwt {
        private boolean success;
        private String accessToken;
        private Long memberId;   // 로그인한 회원 PK
        private String username; // 로그인 아이디
        private String name;     // 실제 이름 (선택)
    }