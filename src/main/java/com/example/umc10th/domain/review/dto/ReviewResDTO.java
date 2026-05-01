package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReviewResDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateResultDTO {
        private Long reviewId;
        private int rating;
        private String comment;
    }
}
