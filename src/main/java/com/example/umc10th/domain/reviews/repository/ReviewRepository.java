package com.example.umc10th.domain.reviews.repository;

import com.example.umc10th.domain.reviews.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {
}