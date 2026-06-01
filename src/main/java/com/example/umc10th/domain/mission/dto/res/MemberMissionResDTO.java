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

    // 미션 목록 DTO (status 값으로 진행 중/완료 상태를 구분)
    @Builder
    public record MissionListDTO(
            List<MemberMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
