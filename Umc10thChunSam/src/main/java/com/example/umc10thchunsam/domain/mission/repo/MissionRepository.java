package com.example.umc10thchunsam.domain.mission.repo;

import com.example.umc10thchunsam.domain.mission.dto.LocalMissionOfferResponse;
import com.example.umc10thchunsam.domain.mission.entity.Mission;
import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;



public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select new com.example.umc10thchunsam.domain.mission.dto.LocalMissionOfferResponse(
            r.id,
            r.restaurantName,
            m.givePoint,
            m.validDate
        )
        from Mission m
        join m.memberMissions miss
        join m.restourant r
        join r.location loc
        where loc.id = :localId
          and miss.issuccess = :status
          and miss.member.id = :memberId
    """)
    Page<LocalMissionOfferResponse> findMissionOffersByLocationId(@Param("localId") Long localId,
                                                                    @Param("memberId") Long memberId,
                                                                    @Param("missionStatus")MissionStatus status,
                                                                    Pageable pageable);

    @Query("""
    select m from Mission m
    join fetch m.restourant
    where m.restourant.id = :storeId
""")
    Page<Mission> findByRestourant_Id(Long restaurantId, Pageable pageable);


}


