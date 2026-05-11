package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.Mission;
import com.example.umc_spring.domain.mission.entity.UserMission;
import com.example.umc_spring.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findAllByMemberIdAndMissionStatus(
            Long memberId,
            MissionStatus missionStatus,
            Pageable pageable
    );

    @Query("""
            SELECT m
            FROM Mission m
            JOIN m.restaurant r
            WHERE r.restaurantLocation = :location
            ORDER BY m.id DESC
            """)
    Page<Mission> findHomeMissions(
            String location,
            Pageable pageable
    );
}