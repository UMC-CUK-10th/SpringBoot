package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행중(ONGOING) 미션 목록
    @Query(value = """
                SELECT ms
                FROM MemberMission ms
                JOIN FETCH ms.mission m
                JOIN FETCH m.restaurant r
                WHERE ms.member.id = :memberId
                  AND ms.missionStatus = :status
            """,
            countQuery = """
                SELECT COUNT(ms)
                FROM MemberMission ms
                WHERE ms.member.id = :memberId
                  AND ms.missionStatus = :status
            """)
    Page<MemberMission> findByMemberAndStatusOngoing(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    // 완료(COMPLETED) 미션 목록
    @Query(value = """
                SELECT ms
                FROM MemberMission ms
                JOIN FETCH ms.mission m
                JOIN FETCH m.restaurant r
                WHERE ms.member.id = :memberId
                  AND ms.missionStatus = :status
            """,
            countQuery = """
                SELECT COUNT(ms)
                FROM MemberMission ms
                WHERE ms.member.id = :memberId
                  AND ms.missionStatus = :status
            """)
    Page<MemberMission> findByMemberAndStatusCompleted(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    // 홈 화면 쿼리
    @Query("""
                SELECT COUNT(mm)
                FROM MemberMission mm
                WHERE mm.member.id = :memberId
                  AND mm.mission.restaurant.region.regionName = :region
                  AND mm.missionStatus = :status
            """)
    Long countCompletedMissionsInRegion(
            @Param("memberId") Long memberId,
            @Param("region") String region,
            @Param("status") MissionStatus status
    );
}
