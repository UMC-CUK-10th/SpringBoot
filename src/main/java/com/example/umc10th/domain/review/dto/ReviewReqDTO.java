package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    //리뷰 쓰기
    public record CreateReview(
            Long storeId,
            String content,
            int rating
    ) {}
}