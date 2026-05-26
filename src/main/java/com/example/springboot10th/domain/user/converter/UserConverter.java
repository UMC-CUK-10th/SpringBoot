package com.example.springboot10th.domain.user.converter;

import com.example.springboot10th.domain.mission.entity.UserMission;
import com.example.springboot10th.domain.user.dto.UserResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserResponseDTO.UserProfileResponse toUserProfileResponse(User user) {
        return UserResponseDTO.UserProfileResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .gender("NONE")
                .build();
    }

    public static UserResponseDTO.UserMissionResponse toUserMissionResponse(UserMission userMission) {
        return UserResponseDTO.UserMissionResponse.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getMission() != null && userMission.getMission().getStore() != null ? userMission.getMission().getStore().getName() : "")
                .point(userMission.getMission() != null ? userMission.getMission().getPoint() : 0)
                .conditional(userMission.getMission() != null ? userMission.getMission().getConditional() : "")
                .status(userMission.getStatus().name())
                .build();
    }

    public static UserResponseDTO.UserMissionListResponse toUserMissionListResponse(Page<UserMission> missionPage) {
        List<UserResponseDTO.UserMissionResponse> missionResponseList = missionPage.stream()
                .map(UserConverter::toUserMissionResponse)
                .collect(Collectors.toList());

        return UserResponseDTO.UserMissionListResponse.builder()
                .missionList(missionResponseList)
                .listSize(missionResponseList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
