package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.MyReviewResponse toMyReviewResponse(Review review) {
        return new ReviewResDTO.MyReviewResponse(
                review.getId(),
                review.getContent(),
                review.getScore(),
                review.getStore().getId(),
                review.getStore().getName()
        );
    }

    public static ReviewResDTO.MyReviewCursorResponse toMyReviewCursorResponse(
            List<Review> reviews,
            boolean hasNext
    ) {
        List<ReviewResDTO.MyReviewResponse> reviewList = reviews.stream()
                .map(ReviewConverter::toMyReviewResponse)
                .toList();

        Long nextCursorId = null;
        Float nextCursorScore = null;

        if (!reviews.isEmpty()) {
            Review lastReview = reviews.get(reviews.size() - 1);
            nextCursorId = lastReview.getId();
            nextCursorScore = lastReview.getScore();
        }

        return new ReviewResDTO.MyReviewCursorResponse(
                reviewList,
                reviewList.size(),
                nextCursorId,
                nextCursorScore,
                hasNext
        );
    }
}