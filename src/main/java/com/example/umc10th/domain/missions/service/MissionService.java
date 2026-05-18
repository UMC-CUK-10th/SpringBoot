package com.example.umc10th.domain.missions.service;

import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.repository.MemberRepository;
import com.example.umc10th.domain.missions.converter.MissionConverter;
import com.example.umc10th.domain.missions.dto.MissionResDTO;
import com.example.umc10th.domain.missions.entity.Missions;
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
    public MissionResDTO.Pagination<MissionResDTO.MissionViewDTO> getMemberMissionPagination(
            Long memberId,
            Boolean isCompleted,
            Integer pageNumber,
            Integer pageSize) {

        // 사용자 존재 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 오프셋 기반 PageRequest 생성
        PageRequest pageRequest = PageRequest.of(pageNumber > 0 ? pageNumber - 1 : 0, pageSize, Sort.by("createdAt").descending());

        // DB 조회
        Page<MemberMissions> missionPage = memberMissionRepository.findByMemberIdAndStatus(memberId, isCompleted, pageRequest);

        // 페이지네이션 DTO로 변환
        return MissionConverter.toPagination(
                missionPage.map(MissionConverter::toMissionViewDTO).toList(),
                missionPage.getNumber(),
                missionPage.getSize()
        );
    }
}