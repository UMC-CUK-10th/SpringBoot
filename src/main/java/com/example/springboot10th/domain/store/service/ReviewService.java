package com.example.springboot10th.domain.store.service;

import com.example.springboot10th.domain.store.dto.ReviewRequestDTO;
import com.example.springboot10th.domain.store.entity.Review;

import com.example.springboot10th.domain.store.dto.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO.CreateReviewResponse createReview(Long userId, Long storeId, ReviewRequestDTO.CreateReviewDTO request);
    ReviewResponseDTO.ReviewCursorPaginationResponse getMyReviewsWithCursor(Long userId, Long cursorId, Float cursorScore, Integer pageSize, String sortBy);
}
