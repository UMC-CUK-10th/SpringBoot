package com.example.springboot10th.domain.user.service;

import com.example.springboot10th.domain.mission.entity.UserMission;
import com.example.springboot10th.domain.mission.enums.MissionStatus;
import com.example.springboot10th.domain.mission.repository.UserMissionRepository;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot10th.domain.user.converter.UserConverter;
import com.example.springboot10th.domain.user.dto.UserRequestDTO;
import com.example.springboot10th.domain.user.dto.UserResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public UserResponseDTO.UserProfileResponse getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserConverter.toUserProfileResponse(user);
    }

    @Override
    public UserResponseDTO.UserMissionListResponse getMyMissions(Long userId, String status, Integer page) {
        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());
        Page<UserMission> missionPage = userMissionRepository.findMyMissions(userId, missionStatus, PageRequest.of(page, 10));
        return UserConverter.toUserMissionListResponse(missionPage);
    }

    @Override
    public UserResponseDTO.UserMissionListResponse getMyInProgressMissions(UserRequestDTO.GetInProgressMissionsRequest request) {
        Page<UserMission> missionPage = userMissionRepository.findMyMissions(
                request.getUserId(),
                MissionStatus.CHALLENGING,
                PageRequest.of(request.getPageNumber(), request.getPageSize())
        );
        return UserConverter.toUserMissionListResponse(missionPage);
    }
}
