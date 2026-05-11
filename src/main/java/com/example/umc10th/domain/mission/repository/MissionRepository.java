package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 진행중 / 완료 미션 조회
    @Query("SELECT mm FROM MemberMission mm " +
            "WHERE mm.member.memberId = :memberId AND mm.status = :status")
    Page<MemberMission> findMemberMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            Pageable pageable
    );

    // 홈 화면 (지역 기반)
    @Query("SELECT m FROM Mission m WHERE m.regionId = :regionId")
    Page<Mission> findByRegion(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}