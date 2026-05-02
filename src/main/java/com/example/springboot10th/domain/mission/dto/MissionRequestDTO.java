package com.example.springboot10th.domain.mission.dto;

import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class ReviewRequest {
        private Long missionId; 
        private Float rating;
        private String content;
    }

    @Getter
    public static class ReviewEditRequest {
        private Float rating;
        private String content;
    }
}
