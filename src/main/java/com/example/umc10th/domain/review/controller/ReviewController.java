package com.example.umc10th.domain.review.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateReviewResponse>> createReview(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewReqDTO.CreateReviewRequest request
    ) {

        ReviewResDTO.CreateReviewResponse response = ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(1L)
                .userMissionId(request.userMissionId())
                .rating(request.rating())
                .content(request.content())
                .imageUrls(List.of(
                        "https://example.com/reviews/1.jpg",
                        "https://example.com/reviews/2.jpg"
                ))
                .createdAt(LocalDateTime.of(2026, 3, 26, 16, 0))
                .build();

        return ResponseEntity
                .status(ReviewSuccessCode.REVIEW_CREATED.getStatus())
                .body(ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, response));
    }
}