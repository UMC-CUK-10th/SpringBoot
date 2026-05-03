package com.example.umc_spring.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionPreviewDTO {
        private Long missionId;
        private String restaurantName;
        private String missionContent;
        private Integer rewardPoint;
        private String missionStatus;
    }

    @Getter
    @Builder
    public static class MissionListDTO {
        private List<MissionPreviewDTO> missionList;
        private Integer page;
        private Integer size;
        private Boolean hasNext;
    }

    @Getter
    @Builder
    public static class MissionChallengeResultDTO {
        private Long missionId;
    }

    @Getter
    @Builder
    public static class MissionSuccessResultDTO {
        private Long userMissionId;
    }
}