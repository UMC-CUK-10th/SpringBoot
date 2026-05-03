package com.example.umc_spring.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private Long restaurantId;
        private Integer reviewScore;
        private String reviewContent;
    }
}