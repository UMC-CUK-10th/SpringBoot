package com.example.umc10th.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionReqDTO {

    // 7주차 예제 - 식당 미션 생성 DTO
    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}

    // 나의 미션 목록 조회 DTO
    public record GetMyMissionListDTO(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long memberId
    ) {}

    // 미션 성공 누르기 DTO
    public record CompleteMissionDTO(
            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId,
            @NotNull(message = "미션 완료 여부는 필수입니다.")
            Boolean isCompleted,
            LocalDateTime completedAt,
            LocalDateTime updatedAt
    ) {}
}
