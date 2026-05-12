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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public User getMyPage(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public Page<UserMission> getMyMissions(Long userId, String status, Integer page) {
        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());
        return userMissionRepository.findMyMissions(userId, missionStatus, PageRequest.of(page, 10));
    }
}
