package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Member signUp(MemberReqDTO.SignUpDTO request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        if (memberRepository.existsByName(request.getNickname())) {
            throw new RuntimeException("이미 사용 중인 닉네임입니다.");
        }

        Member member = MemberConverter.toMember(request);

        return memberRepository.save(member);
    }

    public MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(member);

        return MemberConverter.toLoginResultDTO(member, accessToken);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MyPageResponseDTO getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        return MemberConverter.toMyPageResponseDTO(member);
    }
}