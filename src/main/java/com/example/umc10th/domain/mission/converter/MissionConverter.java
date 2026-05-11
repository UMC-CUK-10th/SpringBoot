package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionUser;

public class MissionConverter {

    // 내 미션 목록 조회
    public static MissionResponseDTO.GetInfo toGetInfo(
            Mission mission,
            Boolean isCompleted
    )
    {
        return MissionResponseDTO.GetInfo.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .content(mission.getContent())
                .missionPoint(mission.getPoint())
                .isCompleted(isCompleted)
                .build();
    }
}
