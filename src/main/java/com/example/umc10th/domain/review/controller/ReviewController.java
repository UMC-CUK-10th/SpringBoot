package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions/{missionId}/reviews")
public class ReviewController {

    // 리뷰 작성
    @PostMapping
    public ApiResponse<Void> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewReqDTO.CreateDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_OK, null);
    }
}
