package com.example.umc_spring.domain.review.converter;

import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }
}
