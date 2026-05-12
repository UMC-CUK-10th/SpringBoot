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
@RequestMapping("/api")
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 작성 API
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long restaurantId,
            @RequestParam("memberId") Long memberId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, reviewCommandService.createReview(restaurantId, memberId, request)
        );
    }

    // 리뷰 조회 API (커서 기반 페이징)
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO>> getReviews(
            @PathVariable Long restaurantId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_FOUND_OK, reviewCommandService.getReviews(restaurantId, pageSize, cursor, query));
    }
}
