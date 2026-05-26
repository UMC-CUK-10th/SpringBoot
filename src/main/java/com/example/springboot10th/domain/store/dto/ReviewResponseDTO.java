package com.example.springboot10th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResponse {
        private Long reviewId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDetailResponse {
        private Long reviewId;
        private String storeName;
        private String nickname;
        private String content;
        private Float score;
        private java.time.LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCursorPaginationResponse {
        private java.util.List<ReviewDetailResponse> reviewList;
        private Long nextCursorId;
        private Float nextCursorScore;
        private Boolean hasNext;
    }
}
