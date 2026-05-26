package com.example.springboot10th.global.security;

import com.example.springboot10th.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Spring Security의 UserDetails를 구현한 클래스.
 * 인증이 완료되면 SecurityContext에 저장되는 사용자 정보를 담는다.
 */
@Getter
public class AuthMember implements UserDetails {

    private final Long userId;
    private final String email;
    private final String password;

    public AuthMember(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    // 사용자의 권한 목록 반환 (현재는 ROLE_USER 단일 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Spring Security에서 username으로 email을 사용
    @Override
    public String getUsername() {
        return email;
    }

    // 계정 만료 여부 (true = 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 (true = 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명 만료 여부 (true = 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 (true = 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
