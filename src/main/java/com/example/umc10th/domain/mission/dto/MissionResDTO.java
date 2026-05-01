package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @AllArgsConstructor
    public static class MissionItemDTO {
        private Long missionId;
        private String title;
        private String status;
    }

    @Getter
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionItemDTO> missions;
    }
}