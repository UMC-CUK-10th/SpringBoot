package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    // 내가 작성한 리뷰 조회 - ID 순 첫 페이지
    @Query("""
            SELECT r
            FROM Review r
            JOIN FETCH r.store
            WHERE r.member.id = :memberId
            ORDER BY r.id DESC
            """)
    List<Review> findMyReviewsOrderByIdFirst(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 조회 - ID 순 다음 페이지
    @Query("""
            SELECT r
            FROM Review r
            JOIN FETCH r.store
            WHERE r.member.id = :memberId
              AND r.id < :cursorId
            ORDER BY r.id DESC
            """)
    List<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 조회 - 별점 순 첫 페이지
    @Query("""
            SELECT r
            FROM Review r
            JOIN FETCH r.store
            WHERE r.member.id = :memberId
            ORDER BY r.star DESC, r.id DESC
            """)
    List<Review> findMyReviewsOrderByStarFirst(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 조회 - 별점 순 다음 페이지
    @Query("""
            SELECT r
            FROM Review r
            JOIN FETCH r.store
            WHERE r.member.id = :memberId
              AND (
                    r.star < :cursorStar
                    OR (r.star = :cursorStar AND r.id < :cursorId)
                  )
            ORDER BY r.star DESC, r.id DESC
            """)
    List<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("cursorStar") Integer cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}