package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    // 미션 목록 조회 DTO
    @Builder
    public record MissionDTO(
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
    public record MissionListDTO(
            List<MissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 진행 중인 미션 조회 DTO
    @Builder
    public record InProgressMissionDTO(
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
    public record InProgressMissionListDTO(
            List<InProgressMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 진행 완료한 미션 조회 DTO
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
