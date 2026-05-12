package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    @PostMapping
    public ApiResponse<String> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReview request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "리뷰 작성"
        );
    }
}