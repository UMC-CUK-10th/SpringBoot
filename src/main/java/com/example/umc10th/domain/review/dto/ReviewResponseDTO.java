package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    // 리뷰 작성
    @Builder
    public record CreateReview(

            String content,
            Float star,
            LocalDateTime createdAt,
            String name,
            String storeName

    ){}
}
