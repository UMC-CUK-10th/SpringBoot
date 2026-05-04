package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionListResponse(
            List<MissionInfoResponse> missions,
            Integer page,
            Integer size,
            Boolean hasNext
    ) {
    }

    @Builder
    public record MissionInfoResponse(
            Long userMissionId,
            Long missionId,
            Integer rewardPoint,
            String storeName,
            String missionContent,
            String status,
            LocalDateTime completedAt
    ) {
    }

    @Builder
    public record MissionDetailResponse(
            Long missionId,
            String storeName,
            String category,
            String missionContent,
            Integer rewardPoint,
            String status,
            String successStoreCode
    ) {
    }

    @Builder
    public record MissionSuccessResponse(
            Long userMissionId,
            String status,
            LocalDateTime completedAt,
            Integer rewardPoint
    ) {
    }
}