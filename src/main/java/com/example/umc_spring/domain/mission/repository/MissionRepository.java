package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(
            value = """
                    SELECT m
                    FROM Mission m
                    JOIN m.restaurant r
                    WHERE r.restaurantLocation = :location
                    """,
            countQuery = """
                    SELECT COUNT(m)
                    FROM Mission m
                    JOIN m.restaurant r
                    WHERE r.restaurantLocation = :location
                    """
    )
    Page<Mission> findHomeMissions(
            @Param("location") String location,
            Pageable pageable
    );
}