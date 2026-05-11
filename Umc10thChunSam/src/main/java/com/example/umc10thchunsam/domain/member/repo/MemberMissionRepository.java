package com.example.umc10thchunsam.domain.member.repo;

import com.example.umc10thchunsam.domain.member.entity.mapping.MemberMission;
import com.example.umc10thchunsam.domain.mission.entity.Mission;
import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {



    @Query("select m from MemberMission m where m.id = :id")
    List<MemberMission> findByid(@Param("id") Long id);

    @Query("select m from MemberMission m where m.id = :id and m.issuccess = :status")
    List<MemberMission> findByidAndIssuccess(@Param("id")  Long id,@Param("status") MissionStatus status);


    //유저 ID와 일치하고 , Progressing인 미션 페이지들 받기
    @Query("""
        select mm.mission
        from MemberMission mm
        where mm.mission.id = :missionId
          and mm.issuccess = :status
    """)
    Page<Mission> findMissionsByMissionIdAndStatus(
            @Param("missionId") Long missionId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
