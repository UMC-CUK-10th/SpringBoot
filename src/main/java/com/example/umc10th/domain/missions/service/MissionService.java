package com.example.umc10th.domain.missions.service;

import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.repository.MemberRepository;
import com.example.umc10th.domain.missions.entity.mapping.MemberMissions;
import com.example.umc10th.domain.missions.repository.MemberMissionRepository;
import com.example.umc10th.domain.missions.repository.MissionRepository;
import com.example.umc10th.domain.reviews.exception.code.ReviewErrorCode;
import com.example.umc10th.global.apiPayload.exception.GeneralHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<MemberMissions> getMemberMissionList(Long memberId, Boolean isCompleted, Integer page) {
        // 사용자 존재 여부 조회
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ReviewErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page > 0 ? page - 1 : 0, 10, Sort.by("createdAt").descending());

        return memberMissionRepository.findByMemberIdAndStatus(memberId, isCompleted, pageRequest);
    }
}