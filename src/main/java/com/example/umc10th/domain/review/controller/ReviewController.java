package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{missionId}/reviews")
    public ApiResponse<Void> createReview(@PathVariable Long missionId) {

        reviewService.createReview(1L, missionId, "리뷰", 5.0f);

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED_OK, null);
    }
}
