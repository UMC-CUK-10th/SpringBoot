package com.example.umc10th.domain.reviews.repository;

import com.example.umc10th.domain.reviews.entity.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    // ID 순 (최신순) 커서 기반 조회
    @Query("SELECT r FROM Reviews r JOIN FETCH r.stores WHERE r.members.memberId = :memberId " +
            "AND (:lastId IS NULL OR r.reviewId < :lastId) ORDER BY r.reviewId DESC")
    Page<Reviews> findAllByMemberIdOrderByReviewIdDesc(@Param("memberId") Long memberId, @Param("lastId") Long lastId, Pageable pageable);

    // 2. 별점 순 커서 기반 조회 (별점이 같으면 ID 순으로 정렬하는 복합 커서)
    @Query("SELECT r FROM Reviews r JOIN FETCH r.stores WHERE r.members.memberId = :memberId " +
            "AND (:lastRating IS NULL OR r.rating < :lastRating OR (r.rating = :lastRating AND r.reviewId < :lastId)) " +
            "ORDER BY r.rating DESC, r.reviewId DESC")
    Page<Reviews> findAllByMemberIdOrderByRatingDesc(@Param("memberId") Long memberId, @Param("lastRating") Float lastRating, @Param("lastId") Long lastId, Pageable pageable);
}