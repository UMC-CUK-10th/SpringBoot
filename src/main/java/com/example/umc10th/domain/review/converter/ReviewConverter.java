package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

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
}