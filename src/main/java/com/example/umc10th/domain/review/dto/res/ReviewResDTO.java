package com.example.umc10th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    // 리뷰 작성 DTO
    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            Long restId,
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
