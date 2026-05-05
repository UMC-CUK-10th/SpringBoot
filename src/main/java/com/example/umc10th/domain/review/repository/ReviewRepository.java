package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            SELECT COUNT(r) > 0
            FROM Review r
            WHERE r.member.id = :memberId
              AND r.store.id = :storeId
            """)
    boolean existsReviewByMemberAndStore(
            @Param("memberId") Long memberId,
            @Param("storeId") Long storeId
    );
}