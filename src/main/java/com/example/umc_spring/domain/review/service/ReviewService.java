package com.example.umc_spring.domain.review.service;

import com.example.umc_spring.domain.review.entity.Review;

public interface ReviewService {
    Review findReview(Long reviewId);
}
