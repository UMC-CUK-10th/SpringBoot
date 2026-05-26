package com.example.springboot10th.domain.store.repository;

import com.example.springboot10th.domain.store.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.id DESC")
    List<Review> findMyReviewsOrderByIdDesc(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.id < :cursorId ORDER BY r.id DESC")
    List<Review> findMyReviewsOrderByIdDescCursor(@Param("userId") Long userId, @Param("cursorId") Long cursorId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.score DESC, r.id DESC")
    List<Review> findMyReviewsOrderByScoreDesc(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND (r.score < :cursorScore OR (r.score = :cursorScore AND r.id < :cursorId)) ORDER BY r.score DESC, r.id DESC")
    List<Review> findMyReviewsOrderByScoreDescCursor(@Param("userId") Long userId, @Param("cursorScore") Float cursorScore, @Param("cursorId") Long cursorId, Pageable pageable);
}
