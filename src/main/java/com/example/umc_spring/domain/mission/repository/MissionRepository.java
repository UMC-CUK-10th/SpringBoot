package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
