package com.example.springboot10th.domain.user.service;

import com.example.springboot10th.domain.mission.entity.UserMission;
import com.example.springboot10th.domain.user.entity.User;
import org.springframework.data.domain.Page;

import com.example.springboot10th.domain.user.dto.UserRequestDTO;
import com.example.springboot10th.domain.user.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO.UserProfileResponse getMyPage(Long userId);
    UserResponseDTO.UserMissionListResponse getMyMissions(Long userId, String status, Integer page);
    UserResponseDTO.UserMissionListResponse getMyInProgressMissions(UserRequestDTO.GetInProgressMissionsRequest request);
}
