package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회 DTO
    @Builder
    public record PreviewMissionDTO(
            Long missionId,
            String content,
            Long price,
            Long point,
            LocalDateTime deadline
    ) {}

    @Builder
    public record PreviewMissionListDTO(
            List<PreviewMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 미션 성공 누르기 DTO
    @Builder
    public record CompletedMissionDTO(
            Long memberMissionId,
            Long missionId,
            Long restId,
            String restName,
            String content,
            Long price,
            Long point,
            LocalDateTime deadline,
            String status,
            LocalDateTime updatedAt
    ) {}

    @Builder
    public record CompletedMissionListDTO(
            List<CompletedMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
