package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record SignUpResponse(
            Long userId,
            String email,
            String nickname
    ) {
    }

    @Builder
    public record HomeResponse(
            String nickname,
            Integer point,
            Integer completedMissionCount,
            Integer goalMissionCount,
            Integer goalRewardPoint,
            List<HomeMissionResponse> missions
    ) {
    }

    @Builder
    public record HomeMissionResponse(
            Long missionId,
            String storeName,
            String category,
            String missionContent,
            Integer rewardPoint,
            Integer dDay
    ) {
    }

    @Builder
    public record PointResponse(
            Integer totalPoint,
            List<PointHistoryResponse> histories,
            Integer page,
            Integer size,
            Boolean hasNext
    ) {
    }

    @Builder
    public record PointHistoryResponse(
            Long pointHistoryId,
            String type,
            Integer amount,
            String description,
            LocalDateTime createdAt
    ) {
    }
}