package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // [추가됨] ID 커서 기반 (최신순)
    @Query("SELECT r FROM Review r JOIN FETCH r.store WHERE r.member.id = :memberId AND (:cursorId IS NULL OR r.id < :cursorId) ORDER BY r.id DESC")
    List<Review> findMyReviewsByIdCursor(@Param("memberId") Long memberId, @Param("cursorId") Long cursorId, Pageable pageable);

    // [추가됨] 별점 커서 기반 (별점 높은 순, 동일 별점일 경우 최신순)
    @Query("SELECT r FROM Review r JOIN FETCH r.store WHERE r.member.id = :memberId " +
            "AND (:cursorStar IS NULL OR r.star < :cursorStar OR (r.star = :cursorStar AND r.id < :cursorId)) " +
            "ORDER BY r.star DESC, r.id DESC")
    List<Review> findMyReviewsByStarCursor(@Param("memberId") Long memberId, @Param("cursorStar") BigDecimal cursorStar, @Param("cursorId") Long cursorId, Pageable pageable);
}