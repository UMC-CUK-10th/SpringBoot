package com.example.umc_spring.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class ReviewPreviewDTO {
        Long reviewId;
        String content;
        Long score;
    }
}
