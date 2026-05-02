package com.example.springboot.domain.review.dto;

public class ReviewReqDTO {
    // 2. 리뷰 작성
    public record WriteDTO(
            String review_content,
            Integer favorite,
            String review_URL
    ) {}
}
