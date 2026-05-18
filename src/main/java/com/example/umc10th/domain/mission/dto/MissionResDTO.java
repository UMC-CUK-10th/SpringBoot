package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Integer totalPages,
            Long totalElements,
            Boolean hasNext
    ) {
    }
}