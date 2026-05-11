package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    /* ───────────────────────────────────────────
       과제 2: 내가 작성한 리뷰 목록 응답
       (사진 제외, 커서 기반 페이지네이션)
    ─────────────────────────────────────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewListRes {
        private List<ReviewRes> reviews;

        /** 다음 페이지 요청에 사용할 커서 ID (마지막 리뷰 ID) */
        private Long nextCursorId;

        /** 별점 순 정렬일 때 사용할 다음 커서 별점 */
        private Float nextCursorScore;

        /** 다음 페이지 존재 여부 */
        private boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewRes {
        private Long reviewId;
        private String storeName;       // 가게 이름
        private Float score;            // 별점
        private String body;            // 리뷰 본문
        private String ownerReply;      // 사장님 답글 (없으면 null)
        private LocalDateTime createdAt;
    }
}