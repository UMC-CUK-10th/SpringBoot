package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // (기존 코드) 내가 진행중/완료한 미션 목록 조회
    @Query(value = "SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store WHERE mm.member.id = :memberId",
            countQuery = "SELECT count(mm) FROM MemberMission mm WHERE mm.member.id = :memberId")
    Page<MemberMission> findMyMissions(@Param("memberId") Long memberId, Pageable pageable);

    // [추가됨] 진행 중인 미션만 오프셋 기반으로 조회
    @Query(value = "SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store WHERE mm.member.id = :memberId AND mm.isComplete = false",
            countQuery = "SELECT count(mm) FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.isComplete = false")
    Page<MemberMission> findOngoingMissions(@Param("memberId") Long memberId, Pageable pageable);
}