package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.GetMission toGetMission(Mission mission) {
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .build();
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Integer totalPages,
            Long totalElements,
            Boolean hasNext
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .hasNext(hasNext)
                .build();
    }
}