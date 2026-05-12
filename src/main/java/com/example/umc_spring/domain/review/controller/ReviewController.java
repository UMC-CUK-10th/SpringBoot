package com.example.umc_spring.domain.review.controller;

import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.service.ReviewService;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/my/id")
    public ApiResponse<ReviewResDTO.MyReviewCursorDTO> getMyReviewsOrderById(
            @RequestBody @Valid ReviewReqDTO.MyReviewCursorRequestDTO request
    ) {
        return ApiResponse.onSuccess(
                reviewService.getMyReviewsOrderById(request)
        );
    }

    @PostMapping("/my/star")
    public ApiResponse<ReviewResDTO.MyReviewCursorDTO> getMyReviewsOrderByStar(
            @RequestBody @Valid ReviewReqDTO.MyReviewCursorRequestDTO request
    ) {
        return ApiResponse.onSuccess(
                reviewService.getMyReviewsOrderByStar(request)
        );
    }
}