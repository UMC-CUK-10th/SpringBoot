package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionListResponseDTO toMissionListResponseDTO(
            Page<Mission> missionPage
    ) {
        List<MissionResDTO.MissionPreviewDTO> missions = missionPage
                .stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResDTO.MissionListResponseDTO.builder()
                .missions(missions)
                .pageInfo(toPageInfoDTO(missionPage))
                .build();
    }

    private static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        long dDay = ChronoUnit.DAYS.between(
                LocalDate.now(),
                mission.getDeadline()
        );

        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())

                // Store 엔티티에 category 필드가 없어서 임시값
                .category("카테고리 없음")

                // Mission 엔티티 기준
                .missionContent(mission.getConditional())
                .rewardPoint(mission.getReward())

                .dDay(dDay)
                .build();
    }

    public static MissionResDTO.MyMissionListResponseDTO toMyMissionListResponseDTO(
            Page<MemberMission> memberMissionPage
    ) {
        List<MissionResDTO.MyMissionDTO> missions = memberMissionPage
                .stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();

        return MissionResDTO.MyMissionListResponseDTO.builder()
                .missions(missions)
                .pageInfo(toPageInfoDTO(memberMissionPage))
                .build();
    }

    private static MissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        Boolean isComplete = memberMission.getIsComplete();

        return MissionResDTO.MyMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getConditional())
                .rewardPoint(mission.getReward())
                .isComplete(isComplete)
                .status(Boolean.TRUE.equals(isComplete) ? "성공" : "진행중")
                .buttonText(Boolean.TRUE.equals(isComplete) ? "리뷰 남기기" : "진행중")
                .build();
    }

    private static MissionResDTO.PageInfoDTO toPageInfoDTO(Page<?> page) {
        return MissionResDTO.PageInfoDTO.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .build();
    }
}