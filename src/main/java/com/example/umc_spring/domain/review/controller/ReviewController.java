package com.example.umc_spring.domain.review.controller;

import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long restaurantId,
            @RequestBody ReviewReqDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewResDTO.CreateReviewResultDTO.builder()
                        .reviewId(1L)
                        .restaurantId(restaurantId)
                        .reviewScore(request.getReviewScore())
                        .reviewContent(request.getReviewContent())
                        .build()
        );
    }
}