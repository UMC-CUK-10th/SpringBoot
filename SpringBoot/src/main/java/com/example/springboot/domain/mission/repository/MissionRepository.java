package com.example.springboot.domain.mission.repository;

import com.example.springboot.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.region.id = :regionId AND m.missionStatus = 'AVAILABLE'")
    Page<Mission> findAvailableMissionsByRegion(@Param("regionId") Long regionId, Pageable pageable);
}
