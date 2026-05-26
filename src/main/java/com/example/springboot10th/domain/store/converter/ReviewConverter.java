package com.example.springboot10th.domain.store.converter;

import com.example.springboot10th.domain.store.dto.ReviewResponseDTO;
import com.example.springboot10th.domain.store.entity.Review;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.CreateReviewResponse toCreateReviewResponse(Review review) {
        return ReviewResponseDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .build();
    }

    public static ReviewResponseDTO.ReviewDetailResponse toReviewDetailResponse(Review review) {
        return ReviewResponseDTO.ReviewDetailResponse.builder()
                .reviewId(review.getId())
                .storeName(review.getStore() != null ? review.getStore().getName() : "")
                .nickname(review.getUser() != null ? review.getUser().getNickname() : "")
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewCursorPaginationResponse toReviewCursorPaginationResponse(
            List<Review> reviewList, Integer pageSize) {
        
        boolean hasNext = reviewList.size() > pageSize;
        List<Review> actualList = hasNext ? reviewList.subList(0, pageSize) : reviewList;
        
        List<ReviewResponseDTO.ReviewDetailResponse> dtoList = actualList.stream()
                .map(ReviewConverter::toReviewDetailResponse)
                .collect(Collectors.toList());

        Long nextCursorId = null;
        Float nextCursorScore = null;
        if (!actualList.isEmpty() && hasNext) {
            Review lastReview = actualList.get(actualList.size() - 1);
            nextCursorId = lastReview.getId();
            nextCursorScore = lastReview.getScore();
        }

        return ReviewResponseDTO.ReviewCursorPaginationResponse.builder()
                .reviewList(dtoList)
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .hasNext(hasNext)
                .build();
    }
}
