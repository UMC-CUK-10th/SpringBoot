package com.example.umc10th.domain.reviews.dto;

import java.time.LocalDateTime;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            Float rating,
            String imageUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
