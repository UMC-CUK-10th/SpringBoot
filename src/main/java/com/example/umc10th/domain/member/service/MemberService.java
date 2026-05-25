package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.global.security.entity.AuthMember;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResDTO.SignUpResponse signUp(MemberReqDTO.SignUpRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member saved = memberRepository.save(new Member(
                null,
                request.email(),
                passwordEncoder.encode(request.password()),
                "ROLE_USER",
                null,
                null
        ));

        return new MemberResDTO.SignUpResponse(saved.getId(), saved.getEmail());
    }

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(AuthMember member) {
        return MemberConverter.toGetInfo(member.getMember());
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MissionListRes getChallengingMissions(MemberReqDTO.GetMissionListReq request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<MemberMission> missionPage = memberMissionRepository.findByMemberIdAndStatus(
                request.getMemberId(),
                MissionStatus.CHALLENGING,
                pageable
        );

        return MemberConverter.toMissionListRes(missionPage);
    }
}
