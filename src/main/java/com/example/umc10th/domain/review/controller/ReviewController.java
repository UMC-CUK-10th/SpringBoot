package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewQueryService;

    @PostMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewListRes> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.GetReviewListReq request
    ) {
        ReviewResDTO.ReviewListRes result = reviewQueryService.getMyReviews(request);
        return ApiResponse.onSuccess(result);
    }
}