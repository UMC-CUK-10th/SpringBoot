package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class ReviewController {

    // private final ReviewService reviewService;

    // 리뷰 작성 API
    @PostMapping("/restaurants/{restId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long restId,
            @RequestParam("memberId") Long memberId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, null);
    }
}
