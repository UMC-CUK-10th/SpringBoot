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

    // 리뷰 생성
    // POST /api/reviews
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

    // 내가 작성한 리뷰 목록 조회
    // 커서 기반 페이지네이션
    // ID 순, 별점 순 조회 모두 지원
    // GET /api/reviews/my?sortType=ID&cursorId=10&size=10
    // GET /api/reviews/my?sortType=STAR&cursorStar=5&cursorId=10&size=10
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.MyReviewListResponseDTO> getMyReviews(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String authorizationHeader,

            @RequestParam(defaultValue = "ID") String sortType,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) Integer cursorStar,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long memberId = jwtTokenProvider.getMemberIdFromAuthorizationHeader(authorizationHeader);

        ReviewResDTO.MyReviewListResponseDTO response =
                reviewService.getMyReviews(
                        memberId,
                        sortType,
                        cursorId,
                        cursorStar,
                        size
                );

        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_LIST_FOUND,
                response
        );
    }
}