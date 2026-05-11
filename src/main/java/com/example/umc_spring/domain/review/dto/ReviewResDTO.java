package com.example.umc_spring.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewDTO {
        private Long reviewId;
        private String storeName;
        private Integer star;
        private String body;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewCursorDTO {
        private List<MyReviewDTO> reviewList;
        private Integer listSize;
        private Boolean hasNext;
        private Long nextCursorId;
        private Integer nextCursorStar;
    }
}