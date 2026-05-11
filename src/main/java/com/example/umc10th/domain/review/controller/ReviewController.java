package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@SecurityRequirement(name = "JWT")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtTokenProvider jwtTokenProvider;

    public ReviewController(
            ReviewService reviewService,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.reviewService = reviewService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String authorizationHeader,

            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        Long memberId = jwtTokenProvider.getMemberIdFromAuthorizationHeader(authorizationHeader);

        ReviewResDTO.CreateReviewResultDTO result = reviewService.createReview(
                memberId,
                request
        );

        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATE_REVIEW_SUCCESS,
                result
        );
    }
}