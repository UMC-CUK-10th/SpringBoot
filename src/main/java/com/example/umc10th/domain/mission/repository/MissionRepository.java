package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 지역의 미션 목록 조회 (페이징, @Query 사용)
    @Query(value = "SELECT m FROM Mission m JOIN FETCH m.store s WHERE s.location.id = :locationId",
            countQuery = "SELECT count(m) FROM Mission m WHERE m.store.location.id = :locationId")
    Page<Mission> findMissionsByLocationId(@Param("locationId") Long locationId, Pageable pageable);
}