package com.example.umc10thchunsam.domain.auth.service.query;

import com.example.umc10thchunsam.domain.auth.exception.AuthException;
import com.example.umc10thchunsam.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10thchunsam.domain.auth.service.CustomUserDetails;
import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new AuthException(AuthErrorCode.No_Exists_ID));
        return new CustomUserDetails(member);  // ← 여기!!
    }
}
