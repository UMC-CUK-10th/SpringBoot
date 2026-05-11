package com.example.umc10th.domain.reviews.dto;

import java.time.LocalDateTime;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            Float rating,
            String contents, // 추가됨
            String image_url,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {}
}
