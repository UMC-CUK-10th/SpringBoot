package com.example.springboot10th.domain.store.repository;

import com.example.springboot10th.domain.store.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
