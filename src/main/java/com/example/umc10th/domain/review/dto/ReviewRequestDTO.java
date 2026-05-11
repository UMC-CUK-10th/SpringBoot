package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewRequestDTO {

    // 리뷰 작성
    public record CreateReview(

            String content,
            Float star,
            LocalDateTime cratedAt,
            Long userId,
            Long storeId

    ){}
}
