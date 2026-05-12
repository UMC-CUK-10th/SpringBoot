package com.example.springboot10th.domain.store.service;

import com.example.springboot10th.domain.store.dto.ReviewRequestDTO;
import com.example.springboot10th.domain.store.entity.Review;

public interface ReviewService {
    Review createReview(Long userId, Long storeId, ReviewRequestDTO.CreateReviewDTO request);
}
