package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            Long userMissionId,
            Integer rating,
            String content,
            List<String> imageUrls,
            LocalDateTime createdAt
    ) {
    }
}