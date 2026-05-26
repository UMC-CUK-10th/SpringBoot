package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewResDTO {

    public record MyReviewResponse(
            Long reviewId,
            String content,
            Float score,
            Long storeId,
            String storeName
    ) {
    }

    public record MyReviewCursorResponse(
            List<MyReviewResponse> reviewList,
            Integer listSize,
            Long nextCursorId,
            Float nextCursorScore,
            Boolean hasNext
    ) {
    }
}