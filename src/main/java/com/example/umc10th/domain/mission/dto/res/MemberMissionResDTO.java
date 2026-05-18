package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    // 나의 미션 목록 조회 DTO
    @Builder
    public record MemberMissionDTO(
            Long memberMissionId,
            Long missionId,
            Long restId,
            String restName,
            String conditional,
            Long price,
            int point,
            LocalDate deadline,
            String status,
            LocalDateTime updatedAt
    ) {}

    // 페이지네이션 틀 (오프셋 기반 페이지네이션)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Sort sort
    ) {}

    // 진행 중인 미션 조회 DTO
    @Builder
    public record InProgressMissionDTO(
            Long memberMissionId,
            Long missionId,
            Long restId,
            String restName,
            String conditional,
            Long price,
            int point,
            LocalDate deadline,
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
            String conditional,
            Long price,
            int point,
            LocalDate deadline,
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
