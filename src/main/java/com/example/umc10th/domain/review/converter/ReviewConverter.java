package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(
            Review review,
            Long memberMissionId
    ) {
        return new ReviewResDTO.CreateReviewResultDTO(
                review.getId(),
                memberMissionId,
                review.getStar(),
                review.getContent(),
                review.getCreatedAt()
        );
    }

    public static ReviewResDTO.MyReviewListResponseDTO toMyReviewListResponseDTO(
            List<Review> reviews,
            int size,
            String sortType
    ) {
        boolean hasNext = reviews.size() > size;

        List<Review> reviewList = hasNext
                ? reviews.subList(0, size)
                : reviews;

        List<ReviewResDTO.MyReviewDTO> reviewDTOList = reviewList.stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        Long nextCursorId = null;
        Double nextCursorStar = null;

        if (hasNext && !reviewList.isEmpty()) {
            Review lastReview = reviewList.get(reviewList.size() - 1);
            nextCursorId = lastReview.getId();

            if ("STAR".equalsIgnoreCase(sortType)) {
                nextCursorStar = lastReview.getStar();
            }
        }

        ReviewResDTO.CursorInfoDTO cursorInfo = new ReviewResDTO.CursorInfoDTO(
                nextCursorId,
                nextCursorStar,
                size,
                hasNext,
                sortType
        );

        return new ReviewResDTO.MyReviewListResponseDTO(
                reviewDTOList,
                cursorInfo
        );
    }

    private static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return new ReviewResDTO.MyReviewDTO(
                review.getId(),
                review.getStore().getId(),
                review.getStore().getName(),
                review.getStar(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
}