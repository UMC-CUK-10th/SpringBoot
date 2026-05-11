package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.command.ReviewCommandService;
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

    private final ReviewCommandService reviewCommandService;

    // 리뷰 작성 API
    @PostMapping("/restaurants/{restId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long restId,
            @RequestParam("memberId") Long memberId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, reviewCommandService.createReview(restId, memberId, request)
        );
    }
}
