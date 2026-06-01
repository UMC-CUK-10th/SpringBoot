package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 5주차 예제 - 마이페이지 API
    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.SignUpDTO signUp(MemberReqDTO.SignUpDTO dto) {
        if (memberRepository.existsByEmailAndSocialType(dto.email(), SocialType.LOCAL)) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = MemberConverter.toMember(dto, passwordEncoder.encode(dto.password()));
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpDTO(savedMember);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {
        Member member = memberRepository.findByEmailAndSocialType(dto.email(), SocialType.LOCAL)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_INVALID));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_INVALID);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
