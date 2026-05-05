package com.example.umc10th.domain.members.service;

import com.example.umc10th.domain.members.converter.HomeConverter;
import com.example.umc10th.domain.members.dto.HomeResDTO;
import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.repository.MemberRepository;
import com.example.umc10th.domain.missions.entity.mapping.MemberMissions;
import com.example.umc10th.domain.missions.repository.MemberMissionRepository;
import com.example.umc10th.domain.reviews.exception.code.ReviewErrorCode;
import com.example.umc10th.global.apiPayload.exception.GeneralHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 마이페이지 조회
    public Members getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ReviewErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public HomeResDTO.HomeViewDTO getHomeView(Long memberId) {
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 진행 중인 미션 가져오기 (isCompleted = false)
        Page<MemberMissions> ongoingMissions = memberMissionRepository
                .findByMemberIdAndStatus(memberId, false, PageRequest.of(0, 2));

        // 완료된 미션 개수 가져오기 (isCompleted = true)
        Integer completedCount = memberMissionRepository
                .countByMembersMemberIdAndIsCompleted(memberId, true).intValue();

        return HomeConverter.toHomeViewDTO(member, ongoingMissions.getContent(), completedCount);
    }
}