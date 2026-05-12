package com.example.umc_spring.domain.review.converter;

import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getRestaurant().getRestaurantName())
                .star(review.getReviewScore())
                .body(review.getReviewContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewCursorDTO toMyReviewCursorDTO(Slice<Review> reviewSlice) {
        List<ReviewResDTO.MyReviewDTO> reviewList = reviewSlice.getContent()
                .stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        Long nextCursorId = null;
        Integer nextCursorStar = null;

        if (!reviewSlice.getContent().isEmpty() && reviewSlice.hasNext()) {
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
            nextCursorId = lastReview.getId();
            nextCursorStar = lastReview.getReviewScore();
        }

        return ReviewResDTO.MyReviewCursorDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .hasNext(reviewSlice.hasNext())
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .build();
    }
}