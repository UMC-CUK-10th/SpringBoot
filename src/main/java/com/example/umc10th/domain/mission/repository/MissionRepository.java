package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(
            value = """
                SELECT m
                FROM Mission m
                JOIN FETCH m.store s
                WHERE s.location.id = :locationId
                AND m.deletedAt IS NULL
                AND m.deadline >= CURRENT_DATE
                AND NOT EXISTS (
                    SELECT mm
                    FROM MemberMission mm
                    WHERE mm.member.id = :memberId
                    AND mm.mission.id = m.id
                )
                ORDER BY m.deadline ASC
            """,
            countQuery = """
                SELECT COUNT(m)
                FROM Mission m
                JOIN m.store s
                WHERE s.location.id = :locationId
                AND m.deletedAt IS NULL
                AND m.deadline >= CURRENT_DATE
                AND NOT EXISTS (
                    SELECT mm
                    FROM MemberMission mm
                    WHERE mm.member.id = :memberId
                    AND mm.mission.id = m.id
                )
            """
    )
    Page<Mission> findAvailableMissionsByLocationId(
            @Param("memberId") Long memberId,
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}