package com.example.umc10th.domain.reviews.controller;

import com.example.umc10th.domain.reviews.dto.ReviewReqDTO;
import com.example.umc10th.domain.reviews.dto.ReviewResDTO;
import com.example.umc10th.domain.reviews.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    // 리뷰 작성
    @PostMapping("/{store_id}/create")
    public ApiResponse<String> createReview(
            @PathVariable(name = "store_id") Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request) {

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, "리뷰 등록 성공");
    }

    // 리뷰 작성 페이지 정보 조회
    @GetMapping("/{store_id}")
    public ApiResponse<ReviewResDTO.ReviewWritePageDTO> getReviewPageInfo(
            @PathVariable(name = "store_id") Long storeId) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_INFO_OK, null);
    }

    // 나의 리뷰 목록 조회
    @GetMapping("/list")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews() {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_OK, null);
    }
}
