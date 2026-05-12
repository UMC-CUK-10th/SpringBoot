package com.example.springboot10th.domain.user.service;

import com.example.springboot10th.domain.mission.entity.UserMission;
import com.example.springboot10th.domain.user.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User getMyPage(Long userId);
    Page<UserMission> getMyMissions(Long userId, String status, Integer page);
}
