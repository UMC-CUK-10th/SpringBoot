package com.example.umc_spring.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionPreviewDTO {
        Long missionId;
        String title;
        Long rewardPoint;
    }
}
