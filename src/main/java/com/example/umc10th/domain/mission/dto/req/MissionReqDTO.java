package com.example.umc10th.domain.mission.dto.req;

import java.time.LocalDateTime;

public class MissionReqDTO {

    // 미션 성공 누르기 DTO
    public record CompleteMissionDTO(
            Long missionId,
            Boolean isCompleted,
            LocalDateTime completedAt,
            LocalDateTime updatedAt
    ) {}
}
