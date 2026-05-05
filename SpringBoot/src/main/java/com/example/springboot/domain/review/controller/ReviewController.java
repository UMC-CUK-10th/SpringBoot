package com.example.springboot.domain.review.controller;

import com.example.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final com.example.springboot.domain.review.service.ReviewService reviewService;

    // 2. 리뷰 작성
    @PostMapping("/missions/{mission_id}/reviews")
    @Operation(summary = "리뷰 작성 API", description = "특정 미션에 대한 리뷰를 작성합니다.")
    public ApiResponse<ReviewResDTO.WriteResultDTO> writeReview(
            @PathVariable(name = "mission_id") Long missionId,
            @RequestBody ReviewReqDTO.WriteDTO request
    ) {
        ReviewResDTO.WriteResultDTO result = reviewService.writeReview(missionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
