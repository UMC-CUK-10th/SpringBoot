package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /* ───────────────────────────────────────────
       과제 2 - ID 내림차순 (최신순) 커서 기반 페이지네이션
       cursorId 가 null 이면 첫 페이지
    ─────────────────────────────────────────── */

    /** 첫 페이지: cursorId 없음 */
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.ownerReply " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.id DESC")
    List<Review> findByMemberIdOrderByIdDesc(
            @Param("memberId") Long memberId,
            org.springframework.data.domain.Pageable pageable
    );

    /** 다음 페이지: cursorId 기준 이하 */
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.ownerReply " +
            "WHERE r.member.id = :memberId " +
            "AND r.id < :cursorId " +
            "ORDER BY r.id DESC")
    List<Review> findByMemberIdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            org.springframework.data.domain.Pageable pageable
    );

    /* ───────────────────────────────────────────
       과제 2 - 별점 내림차순 커서 기반 페이지네이션
       동점 시 id DESC 로 보조 정렬
       커서: (score, id) 복합 커서
    ─────────────────────────────────────────── */

    /** 첫 페이지: 커서 없음 */
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.ownerReply " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.score DESC, r.id DESC")
    List<Review> findByMemberIdOrderByScoreDesc(
            @Param("memberId") Long memberId,
            org.springframework.data.domain.Pageable pageable
    );

    /**
     * 다음 페이지: 복합 커서
     * - 별점이 cursorScore 보다 낮거나
     * - 별점이 같고 id 가 cursorId 보다 낮은 항목
     */
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.ownerReply " +
            "WHERE r.member.id = :memberId " +
            "AND (r.score < :cursorScore " +
            "     OR (r.score = :cursorScore AND r.id < :cursorId)) " +
            "ORDER BY r.score DESC, r.id DESC")
    List<Review> findByMemberIdWithScoreCursor(
            @Param("memberId") Long memberId,
            @Param("cursorScore") Float cursorScore,
            @Param("cursorId") Long cursorId,
            org.springframework.data.domain.Pageable pageable
    );
}