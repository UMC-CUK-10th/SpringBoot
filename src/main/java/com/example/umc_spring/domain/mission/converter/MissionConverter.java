package com.example.umc_spring.domain.mission.converter;

import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.entity.Mission;
import com.example.umc_spring.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        Mission mission = userMission.getMission();

        return MissionResDTO.MyMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getRestaurant().getRestaurantName())
                .point(mission.getReward())
                .conditional(mission.getMissionCondition())
                .build();
    }

    public static MissionResDTO.MyMissionPageDTO toMyMissionPageDTO(Page<UserMission> userMissionPage) {
        List<MissionResDTO.MyMissionDTO> missionList = userMissionPage.getContent().stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();

        return MissionResDTO.MyMissionPageDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .build();
    }
}