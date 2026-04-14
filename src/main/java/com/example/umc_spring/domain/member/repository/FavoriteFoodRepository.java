package com.example.umc_spring.domain.member.repository;

import com.example.umc_spring.domain.member.entity.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long> {
}
