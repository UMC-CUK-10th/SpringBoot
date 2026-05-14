package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.usermission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMissionResponse toMyMissionResponse(UserMission userMission) {
        return new MissionResDTO.MyMissionResponse(
                userMission.getMission().getId(),
                userMission.getMission().getContent(),
                userMission.getMission().getReward(),
                userMission.getMission().getStore().getId(),
                userMission.getMission().getStore().getName()
        );
    }

    public static MissionResDTO.MyMissionListResponse toMyMissionListResponse(Page<UserMission> userMissionPage) {
        List<MissionResDTO.MyMissionResponse> missionList = userMissionPage.stream()
                .map(MissionConverter::toMyMissionResponse)
                .toList();

        return new MissionResDTO.MyMissionListResponse(
                missionList,
                missionList.size(),
                userMissionPage.getTotalPages(),
                userMissionPage.getTotalElements(),
                userMissionPage.isFirst(),
                userMissionPage.isLast()
        );
    }
}