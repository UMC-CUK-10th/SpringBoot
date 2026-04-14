package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.mapping.MissionExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionExecutionHistoryRepository extends JpaRepository<MissionExecutionHistory, Long> {
}
