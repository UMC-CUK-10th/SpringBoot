package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface MissionUserRepository extends JpaRepository<MissionUser, Long> {

    Optional<MissionUser> findByMissionAndMember(Mission mission, Member member);

    Optional<MissionUser> findByMember(Member member);

    @Query("SELECT mu FROM MissionUser mu WHERE mu.member = :member AND mu.isCompleted = :isCompleted")
    Page<MissionUser> findByMemberAndIsCompleted(
            @Param("member") Member member,
            @Param("isCompleted") Boolean isCompleted,
            Pageable pageable
    );
}
