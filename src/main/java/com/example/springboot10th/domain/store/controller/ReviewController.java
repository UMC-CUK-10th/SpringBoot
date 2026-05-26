package com.example.springboot10th.domain.store.controller;

import com.example.springboot10th.domain.store.dto.ReviewRequestDTO;
import com.example.springboot10th.domain.store.service.ReviewService;
import lombok.RequiredArgsConstructor;
import com.example.springboot10th.domain.store.dto.ReviewResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateReviewResponse> createReview(
            @PathVariable("storeId") Long storeId,
            @RequestParam("userId") Long userId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewService.createReview(userId, storeId, request));
    }
}
