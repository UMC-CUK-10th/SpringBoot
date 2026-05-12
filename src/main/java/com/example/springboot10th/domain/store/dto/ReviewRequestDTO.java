package com.example.springboot10th.domain.store.dto;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDTO {
        private String content;
        private Float score;
    }
}
