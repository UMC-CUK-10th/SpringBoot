package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            Long restId,
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
