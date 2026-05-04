package com.example.umc_spring.domain.review.controller;

import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.service.ReviewService;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long restaurantId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        Long userId = 1L;

        return ApiResponse.onSuccess(
                reviewService.createReview(userId, restaurantId, request)
        );
    }
}