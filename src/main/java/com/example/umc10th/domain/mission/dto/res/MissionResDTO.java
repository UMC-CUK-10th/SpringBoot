package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 7주차 예제 - 식당 내 미션 조회 DTO
    @Builder
    public record GetMission(
       Long missionId,
       Integer point,
       String conditional
    ){}

    // 7주차 예제 - 페이지네이션 틀 (커서 기반 페이지네이션)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

    // 미션 성공 누르기 DTO
    @Builder
    public record CompletedMissionDTO(
            Long memberMissionId,
            Long missionId,
            Long restId,
            String restName,
            String conditional,
            Long price,
            Integer point,
            LocalDate deadline,
            String status,
            LocalDateTime updatedAt
    ) {}
}
