package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionUser;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    // 내 미션 목록 조회 (진행 중, 완료)
    public static MissionResponseDTO.GetMyMissions toGetMyMissions(
            Mission mission,
            Boolean isCompleted
    )
    {
        return MissionResponseDTO.GetMyMissions.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .content(mission.getContent())
                .missionPoint(mission.getPoint())
                .isCompleted(isCompleted)
                .build();
    }

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionRequestDTO.CreatedMission dto
    ){
        return Mission.builder()
                .store(store)
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
