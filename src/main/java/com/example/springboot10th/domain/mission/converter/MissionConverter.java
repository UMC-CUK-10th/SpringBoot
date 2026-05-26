package com.example.springboot10th.domain.mission.converter;

import com.example.springboot10th.domain.mission.dto.MissionRequestDTO;
import com.example.springboot10th.domain.mission.dto.MissionResponseDTO;
import com.example.springboot10th.domain.mission.entity.Mission;
import com.example.springboot10th.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    public static Mission toMission(
            Store store,
            MissionRequestDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    public static MissionResponseDTO.GetMission toGetMission(Mission mission) {
        return MissionResponseDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResponseDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResponseDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
