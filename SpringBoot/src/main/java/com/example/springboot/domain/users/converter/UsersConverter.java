package com.example.springboot.domain.users.converter;

import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.entity.Users;

public class UsersConverter {

    // 마이페이지 정보 변환
    public static UsersResDTO.GetInfo toGetInfo(Users users) {
        return UsersResDTO.GetInfo.builder()
                .email(users.getEmail())
                .nickname(users.getNickname())
                .point(users.getUserPoint())
                .phoneNumber(users.getUserPhoneNumber())
                .build();
    }

    public static UsersResDTO.OngoingMissionListDTO toOngoingMissionListDTO(org.springframework.data.domain.Page<com.example.springboot.domain.users.entity.UserMission> userMissions) {
        java.util.List<com.example.springboot.domain.mission.dto.MissionResDTO.UserMissionListDTO> missionList = userMissions.getContent().stream()
                .map(com.example.springboot.domain.mission.converter.MissionConverter::toUserMissionListDTO)
                .collect(java.util.stream.Collectors.toList());

        return UsersResDTO.OngoingMissionListDTO.builder()
                .isLast(userMissions.isLast())
                .isFirst(userMissions.isFirst())
                .totalPage(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .listSize(missionList.size())
                .missionList(missionList)
                .build();
    }
}
