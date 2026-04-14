package com.example.umc10th.domain.userMission.repository;

import com.example.umc10th.domain.userMission.entity.userMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<userMission, Long> {
}