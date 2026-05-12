package com.example.umc_spring.domain.review.repository;

import com.example.umc_spring.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByMemberId(Long memberId);

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.member.id = :userId
            ORDER BY r.id DESC
            """)
    Slice<Review> findMyReviewsOrderByIdDesc(
            Long userId,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.member.id = :userId
            AND r.id < :cursorId
            ORDER BY r.id DESC
            """)
    Slice<Review> findMyReviewsByIdCursor(
            Long userId,
            Long cursorId,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.member.id = :userId
            ORDER BY r.reviewScore DESC, r.id DESC
            """)
    Slice<Review> findMyReviewsOrderByStarDesc(
            Long userId,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.member.id = :userId
            AND (
                r.reviewScore < :cursorStar
                OR (r.reviewScore = :cursorStar AND r.id < :cursorId)
            )
            ORDER BY r.reviewScore DESC, r.id DESC
            """)
    Slice<Review> findMyReviewsByStarCursor(
            Long userId,
            Integer cursorStar,
            Long cursorId,
            Pageable pageable
    );
}