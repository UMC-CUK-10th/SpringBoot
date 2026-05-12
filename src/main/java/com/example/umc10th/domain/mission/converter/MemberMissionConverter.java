package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {

    // 나의 미션 목록 조회 API
    public static MemberMissionResDTO.MemberMissionDTO toMissionListDTO(MemberMission memberMission) {
        return MemberMissionResDTO.MemberMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .restId(memberMission.getMission().getRestaurant().getId())
                .restName(memberMission.getMission().getRestaurant().getRestName())
                .conditional(memberMission.getMission().getConditional())
                .price(memberMission.getMission().getPrice())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getMissionStatus().name())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }

    public static MemberMissionResDTO.Pagination<MemberMissionResDTO.MemberMissionDTO> toMissionPagination(Page<MemberMission> memberMissions) {
        return MemberMissionResDTO.Pagination.<MemberMissionResDTO.MemberMissionDTO>builder()
                .data(memberMissions.stream()
                        .map(MemberMissionConverter::toMissionListDTO)
                        .toList())
                .pageNumber(memberMissions.getNumber() + 1)
                .pageSize(memberMissions.getSize())
                .sort(memberMissions.getSort())
                .build();
    }

    // 진행 중인 미션 조회 API
    public static MemberMissionResDTO.InProgressMissionDTO toInProgressMissionDTO(MemberMission memberMission) {
        var mission = toMissionListDTO(memberMission);
        return MemberMissionResDTO.InProgressMissionDTO.builder()
                .memberMissionId(mission.memberMissionId())
                .missionId(mission.missionId())
                .restId(mission.restId())
                .restName(mission.restName())
                .conditional(mission.conditional())
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
        var mission = toMissionListDTO(memberMission);
        return MemberMissionResDTO.CompletedMissionDTO.builder()
                .memberMissionId(mission.memberMissionId())
                .missionId(mission.missionId())
                .restId(mission.restId())
                .restName(mission.restName())
                .conditional(mission.conditional())
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
