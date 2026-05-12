package com.example.springboot10th.domain.mission.repository;

import com.example.springboot10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.store.region.name = :regionName")
    Page<Mission> findMissionsByRegionName(
            @Param("regionName") String regionName,
            Pageable pageable);
}
