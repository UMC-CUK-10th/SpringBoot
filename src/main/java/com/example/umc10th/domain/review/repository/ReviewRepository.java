package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserIdOrderByIdDesc(Long userId, Pageable pageable);

    List<Review> findByUserIdAndIdLessThanOrderByIdDesc(
            Long userId,
            Long cursorId,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.user.id = :userId
            AND (
                :cursorScore IS NULL
                OR r.score < :cursorScore
                OR (r.score = :cursorScore AND r.id < :cursorId)
            )
            ORDER BY r.score DESC, r.id DESC
            """)
    List<Review> findMyReviewsOrderByScore(
            @Param("userId") Long userId,
            @Param("cursorScore") Float cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}