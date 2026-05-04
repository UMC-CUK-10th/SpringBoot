package com.example.umc_spring.domain.review.service;

import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;

public interface ReviewService {

    ReviewResDTO.CreateReviewResultDTO createReview(
            Long userId,
            Long restaurantId,
            ReviewReqDTO.CreateReviewDTO request
    );
}