package com.example.springboot.domain.mission.converter;

import com.example.springboot.domain.mission.dto.MissionResDTO;
import com.example.springboot.domain.mission.entity.Mission;
import com.example.springboot.domain.users.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionDetailDTO toMissionDetailDTO(Mission mission) {
        return MissionResDTO.MissionDetailDTO.builder()
                .mission_id(mission.getId())
                .mission_name(mission.getMissionName())
                .mission_content(mission.getMissionContent())
                .mission_point(mission.getMissionPoint().intValue())
                .store_name(mission.getStore().getStoreName())
                .category(mission.getStore().getCategory())
                .build();
    }

    public static List<MissionResDTO.MissionDetailDTO> toMissionDetailDTOList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toMissionDetailDTO)
                .collect(Collectors.toList());
    }

    public static MissionResDTO.UserMissionListDTO toUserMissionListDTO(UserMission userMission) {
        return MissionResDTO.UserMissionListDTO.builder()
                .mission_id(userMission.getMission().getId())
                .mission_name(userMission.getMission().getMissionName())
                .mission_content(userMission.getMission().getMissionContent())
                .user_mission_status(userMission.getUserMissionStatus())
                .started_at(null) // Entity에 시작 시간 필드가 없음
                .complete_at(userMission.getCompleteAt())
                .build();
    }

    public static List<MissionResDTO.UserMissionListDTO> toUserMissionListDTOList(Page<UserMission> userMissions) {
        return userMissions.getContent().stream()
                .map(MissionConverter::toUserMissionListDTO)
                .collect(Collectors.toList());
    }
}
