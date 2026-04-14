package com.example.umc_spring.domain.review.repository;

import com.example.umc_spring.domain.review.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}