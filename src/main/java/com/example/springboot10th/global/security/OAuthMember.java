package com.example.springboot10th.global.security;

import com.example.springboot10th.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;






@Getter
public class OAuthMember implements OAuth2User {

    private final Long userId;
    private final String email;
    private final String nickname;
    private final boolean isNewUser;
    private final Map<String, Object> attributes;

    public OAuthMember(User user, boolean isNewUser, Map<String, Object> attributes) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.isNewUser = isNewUser;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    


    @Override
    public String getName() {
        return String.valueOf(userId);
    }
}
