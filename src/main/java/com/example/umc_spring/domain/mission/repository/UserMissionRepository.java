package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query(
            value = """
                    SELECT um
                    FROM UserMission um
                    JOIN um.mission m
                    JOIN m.restaurant r
                    WHERE um.member.id = :userId
                    AND um.missionStatus = :status
                    """,
            countQuery = """
                    SELECT COUNT(um)
                    FROM UserMission um
                    WHERE um.member.id = :userId
                    AND um.missionStatus = :status
                    """
    )
    Page<UserMission> findMyMissions(
            @Param("userId") Long userId,
            @Param("status") String status,
            Pageable pageable
    );

    Long countByMemberIdAndMissionStatus(Long userId, String missionStatus);
}