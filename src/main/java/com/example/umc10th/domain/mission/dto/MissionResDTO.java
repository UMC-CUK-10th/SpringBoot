package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionListResponseDTO {
        private List<MissionPreviewDTO> missions;
        private PageInfoDTO pageInfo;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private Long storeId;
        private String storeName;
        private String category;
        private String missionContent;
        private Integer rewardPoint;
        private Long dDay;
    }

    // 내가 진행중/진행완료한 미션 목록 응답 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionListResponseDTO {
        private List<MyMissionDTO> missions;
        private PageInfoDTO pageInfo;
    }

    // 내가 진행중/진행완료한 미션 1개 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionDTO {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private String missionContent;
        private Integer rewardPoint;
        private Boolean isComplete;
        private String status;
        private String buttonText;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PageInfoDTO {
        private Integer page;
        private Integer size;
        private Long totalElements;
        private Integer totalPages;
        private Boolean hasNext;
    }
}