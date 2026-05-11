package com.example.umc_spring.domain.member.service;

import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.entity.Member;
import com.example.umc_spring.domain.member.repository.MemberRepository;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.entity.Mission;
import com.example.umc_spring.domain.mission.enums.MissionStatus;
import com.example.umc_spring.domain.mission.repository.MissionRepository;
import com.example.umc_spring.domain.mission.repository.UserMissionRepository;
import com.example.umc_spring.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public MemberResDTO.MyPageDTO getMyPage(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Long reviewCount = reviewRepository.countByMemberId(userId);

        Long inProgressCount = userMissionRepository.countByMemberIdAndMissionStatus(
                userId,
                "IN_PROGRESS"
        );

        Long successCount = userMissionRepository.countByMemberIdAndMissionStatus(
                userId,
                "SUCCESS"
        );

        return MemberResDTO.MyPageDTO.builder()
                .userId(member.getId())
                .userName(member.getUserName())
                .userPoint(member.getUserPoint())
                .reviewCount(reviewCount)
                .inProgressMissionCount(inProgressCount)
                .completedMissionCount(successCount)
                .build();
    }

    @Override
    public MissionResDTO.MissionListDTO getHomeMissions(
            String location,
            Integer page,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missionPage = missionRepository.findHomeMissions(location, pageable);

        List<MissionResDTO.MissionPreviewDTO> missionList = missionPage.getContent()
                .stream()
                .map(mission -> MissionResDTO.MissionPreviewDTO.builder()
                        .missionId(mission.getId())
                        .restaurantName(mission.getRestaurant().getRestaurantName())
                        .restaurantLocation(mission.getRestaurant().getRestaurantLocation())
                        .missionTitle(mission.getMissionTitle())
                        .missionCondition(mission.getMissionCondition())
                        .rewardPoint(mission.getReward())
                        .missionStatus("AVAILABLE")
                        .build())
                .toList();

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionList)
                .page(missionPage.getNumber())
                .size(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}