package com.example.umc_spring.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDTO {
        private Integer reviewScore;
        private String reviewContent;
        private String comment;
    }
}