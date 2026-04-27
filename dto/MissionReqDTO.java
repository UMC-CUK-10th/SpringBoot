package com.example.umc10th.domain.missions.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionReqDTO {
    public record MissionCompleteDTO(
            Long MissionId,
            Boolean isCompleted,
            LocalDateTime completedAt,
            LocalDateTime updatedAt
    ) {}
}