package com.example.umc_spring.domain.mission.repository;

import com.example.umc_spring.domain.mission.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
