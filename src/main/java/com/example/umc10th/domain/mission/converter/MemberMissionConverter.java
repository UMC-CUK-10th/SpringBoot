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

    // 미션 목록 DTO (status 값으로 진행 중/완료 상태를 구분)
    public static MemberMissionResDTO.MissionListDTO toMissionListDTO(Page<MemberMission> memberMissions) {
        return MemberMissionResDTO.MissionListDTO.builder()
                .missionList(memberMissions.stream()
                        .map(MemberMissionConverter::toMissionListDTO)
                        .toList())
                .listSize(memberMissions.getNumberOfElements())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }
}
