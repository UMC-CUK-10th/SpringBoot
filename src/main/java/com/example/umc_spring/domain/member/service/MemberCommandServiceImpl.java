package com.example.umc_spring.domain.member.service;

import com.example.umc_spring.domain.member.converter.MemberConverter;
import com.example.umc_spring.domain.member.dto.MemberReqDTO;
import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.entity.Member;
import com.example.umc_spring.domain.member.repository.MemberRepository;
import com.example.umc_spring.domain.member.security.AuthMember;
import com.example.umc_spring.domain.member.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberResDTO.JoinResultDTO joinMember(MemberReqDTO.JoinDTO request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = MemberConverter.toMember(request, encodedPassword);

        memberRepository.save(member);

        return MemberConverter.toJoinResultDTO(member);
    }

    @Override
    public MemberResDTO.LoginResultDTO loginMember(MemberReqDTO.LoginDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        AuthMember authMember = new AuthMember(member);

        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberConverter.toLoginResultDTO(member, accessToken);
    }

    @Override
    public MemberResDTO.MyPageDTO getMyPage(AuthMember authMember) {

        Member member = authMember.getMember();

        Long reviewCount = 0L;
        Long inProgressMissionCount = 0L;
        Long completedMissionCount = 0L;

        return MemberConverter.toMyPageDTO(
                member,
                reviewCount,
                inProgressMissionCount,
                completedMissionCount
        );
    }
}