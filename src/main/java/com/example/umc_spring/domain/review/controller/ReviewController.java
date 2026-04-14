package com.example.umc_spring.domain.review.controller;

import com.example.umc_spring.domain.review.converter.ReviewConverter;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.service.ReviewService;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResDTO.ReviewPreviewDTO> getReview(@PathVariable Long reviewId) {
        return ApiResponse.onSuccess(
                ReviewConverter.toReviewPreviewDTO(reviewService.findReview(reviewId))
        );
    }
}
