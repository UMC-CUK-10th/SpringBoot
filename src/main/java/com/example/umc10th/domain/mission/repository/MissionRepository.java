package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByRestaurant(Restaurant restaurant);

    List<Mission> findAllByRestaurant_Id(Long restaurantId, PageRequest pageRequest);

    Slice<Mission> findMissionByRestaurant(Restaurant restaurant);

    Slice<Mission> findMissionByRestaurant_IdAndIdLessThanOrderByIdDesc(Long restaurantId, Long idIsLessThan, PageRequest pageRequest);

    Slice<Mission> findMissionByRestaurant_IdOrderByIdDesc(Long restaurantId, PageRequest pageRequest);
}
