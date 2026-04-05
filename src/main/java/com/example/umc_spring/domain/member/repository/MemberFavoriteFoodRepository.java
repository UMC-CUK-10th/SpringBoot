package com.example.umc_spring.domain.member.repository;

import com.example.umc_spring.domain.member.entity.mapping.MemberFavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFavoriteFoodRepository extends JpaRepository<MemberFavoriteFood, Long> {
}
