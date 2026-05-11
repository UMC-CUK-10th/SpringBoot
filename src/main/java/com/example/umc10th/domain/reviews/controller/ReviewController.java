package com.example.umc10th.domain.reviews.controller;

import com.example.umc10th.domain.reviews.dto.ReviewReqDTO;
import com.example.umc10th.domain.reviews.dto.ReviewResDTO;
import com.example.umc10th.domain.reviews.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.reviews.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    // 리뷰 작성
    @PostMapping("/{storeId}/create")
    public ApiResponse<String> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId, // 임시로 쿼리 파라미터로 받음
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request) {
        reviewService.createReview(storeId, memberId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, "리뷰 등록 성공");
    }

    // 리뷰 작성 페이지 정보 조회
    @GetMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewWritePageDTO> getReviewPageInfo(
            @PathVariable Long storeId) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_INFO_OK, null);
    }

    // 나의 리뷰 목록 조회
    @GetMapping("/list")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long lastId,
            @RequestParam(required = false) Float lastRating,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "latest") String sort) {

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_OK,
                reviewService.getMyReviewList(memberId, lastId, lastRating, size, sort));
    }

}