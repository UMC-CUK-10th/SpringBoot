package com.example.umc10thchunsam.domain.auth.service;

import com.example.umc10thchunsam.domain.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {

    private Long id;         // PK 추가
    private String username; // 로그인 아이디
    private String password;
    private String name;

    private List<SimpleGrantedAuthority> authorities;

    public CustomUserDetails(Member member) {
        this.id = member.getId();                // PK 추가!!!
        this.username = member.getUserId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
    @Override public List<SimpleGrantedAuthority> getAuthorities() { return authorities; }

    // 나머지는 true 반환
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
