package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {

    public static MissionResDTO.MissionListDTO toMissionListDTO(Mission mission) {
        return MissionResDTO.MissionListDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MemberMissionListDTO toMemberMissionListDTO(MemberMission mm) {
        return MissionResDTO.MemberMissionListDTO.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .point(mm.getMission().getPoint())
                .conditional(mm.getMission().getConditional())
                .isComplete(mm.getIsComplete())
                .build();
    }
}

