package com.example.umc10th.domain.mission.dto;

import java.time.LocalDateTime;

public class MissionReqDTO {

    // 미션 성공 누르기 API
    public record CompleteMissionDTO(
            Long MissionId,
            Boolean isCompleted,
            LocalDateTime completedAt,
            LocalDateTime updatedAt
    ) {}
}
