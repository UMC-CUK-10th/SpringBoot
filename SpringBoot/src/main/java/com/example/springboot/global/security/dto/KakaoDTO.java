package com.example.springboot.global.security.dto;

import com.example.springboot.domain.users.entity.enums.SocialType;

public class KakaoDTO implements OAuthDTO{
    private final String id;
    private final String email;
    private final String name;

    public KakaoDTO(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
    @Override
    public String getSocialUid() {
        return id;
    }
    @Override
    public String getSocialEmail() {
        return email;
    }
    @Override
    public String getName() {
        return name;
    }
}
