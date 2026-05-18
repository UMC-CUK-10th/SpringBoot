package com.example.umc10th.domain.missions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionReqDTO {
    // 미션 성공 요청
    public record MissionCompleteDTO(
            @NotBlank(message = "사용자 id는 필수입니다.")
            Long MissionId,
            Boolean isCompleted,
            LocalDateTime completedAt,
            LocalDateTime updatedAt
    ) {}

    // 미션 조회
    @Getter
    public static class MissionListConditionDTO {
        @NotBlank(message = "사용자 id는 필수입니다.")
        Long memberId;
    }
}