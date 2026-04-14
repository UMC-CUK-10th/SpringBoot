package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
