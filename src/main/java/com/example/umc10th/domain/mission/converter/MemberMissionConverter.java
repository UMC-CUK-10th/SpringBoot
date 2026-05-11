package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {

    // 미션 목록 조회 API
    public static MemberMissionResDTO.MissionDTO toMissionDTO(MemberMission memberMission) {
        return MemberMissionResDTO.MissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .restId(memberMission.getMission().getRestaurant().getId())
                .restName(memberMission.getMission().getRestaurant().getRestName())
                .content(memberMission.getMission().getContent())
                .price(memberMission.getMission().getPrice())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getMissionStatus().name())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }

    public static MemberMissionResDTO.MissionListDTO toMissionListDTO(Page<MemberMission> memberMissions) {
        return MemberMissionResDTO.MissionListDTO.builder()
                .missionList(memberMissions.stream()
                        .map(MemberMissionConverter::toMissionDTO)
                        .toList())
                .listSize(memberMissions.getNumberOfElements())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }

    // 진행 중인 미션 조회 API
    public static MemberMissionResDTO.InProgressMissionDTO toInProgressMissionDTO(MemberMission memberMission) {
        var mission = toMissionDTO(memberMission);
        return MemberMissionResDTO.InProgressMissionDTO.builder()
                .memberMissionId(mission.memberMissionId())
                .missionId(mission.missionId())
                .restId(mission.restId())
                .restName(mission.restName())
                .content(mission.content())
                .price(mission.price())
                .point(mission.point())
                .deadline(mission.deadline())
                .status(mission.status())
                .updatedAt(mission.updatedAt())
                .build();
    }

    public static MemberMissionResDTO.InProgressMissionListDTO toInProgressMissionListDTO(Page<MemberMission> memberMissions) {
        return MemberMissionResDTO.InProgressMissionListDTO.builder()
                .missionList(memberMissions.stream()
                        .map(MemberMissionConverter::toInProgressMissionDTO)
                        .toList())
                .listSize(memberMissions.getNumberOfElements())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }

    // 진행 완료한 미션 조회 API
    public static MemberMissionResDTO.CompletedMissionDTO toCompletedMissionDTO(MemberMission memberMission) {
        var mission = toMissionDTO(memberMission);
        return MemberMissionResDTO.CompletedMissionDTO.builder()
                .memberMissionId(mission.memberMissionId())
                .missionId(mission.missionId())
                .restId(mission.restId())
                .restName(mission.restName())
                .content(mission.content())
                .price(mission.price())
                .point(mission.point())
                .deadline(mission.deadline())
                .status(mission.status())
                .updatedAt(mission.updatedAt())
                .build();
    }

    public static MemberMissionResDTO.CompletedMissionListDTO toCompletedMissionListDTO(Page<MemberMission> memberMissions) {
        return MemberMissionResDTO.CompletedMissionListDTO.builder()
                .missionList(memberMissions.stream()
                        .map(MemberMissionConverter::toCompletedMissionDTO)
                        .toList())
                .listSize(memberMissions.getNumberOfElements())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }
}
