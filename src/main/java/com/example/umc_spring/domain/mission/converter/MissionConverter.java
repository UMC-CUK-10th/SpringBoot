package com.example.umc_spring.domain.mission.converter;

import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .rewardPoint(mission.getRewardPoint())
                .build();
    }
}
