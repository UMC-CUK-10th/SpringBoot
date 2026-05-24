package com.example.springboot.global.security.dto;

import com.example.springboot.domain.users.entity.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
