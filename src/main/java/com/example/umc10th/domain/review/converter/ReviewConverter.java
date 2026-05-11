package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {


    // 리뷰 작성
    public static ReviewResponseDTO.CreateReview toCreateReview(
            Review review
    ) {

        return ReviewResponseDTO.CreateReview.builder()
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .name(review.getMember().getName())
                .storeName(review.getStore().getName())
                .build();
    }
}
