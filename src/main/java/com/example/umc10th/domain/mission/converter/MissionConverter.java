package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.restaurant.entity.Restaurant;

import java.util.List;

public class MissionConverter {

    // 7주차 예제 - 식당 미션 생성 API
    public static Mission toMission(
            Restaurant restaurant,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .restaurant(restaurant)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 7주차 예제 - 식당 내 미션 조회 API
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    public static MissionResDTO.CompletedMissionDTO toCompletedMissionDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        Restaurant restaurant = mission.getRestaurant();

        return MissionResDTO.CompletedMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .restId(restaurant.getId())
                .restName(restaurant.getRestName())
                .conditional(mission.getConditional())
                .price(mission.getPrice())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(memberMission.getMissionStatus().name())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }

    // 7주차 예제 - 페이지네이션 틀 생성 (커서 기반 페이지네이션)
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
