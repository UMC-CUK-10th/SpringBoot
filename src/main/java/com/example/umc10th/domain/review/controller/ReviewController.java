package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    // Get 방식으로 RequestBody 받고 있는 메서드들 JWT 방식으로 멤버 id 받는 걸로 바꿔야 함

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/reviews")
    public APIResponse<ReviewResponseDTO.CreateReview> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateReview dto
    ){

        ReviewResponseDTO.CreateReview resDTO = reviewService.createReview(dto);
        BaseSuccessCode code = ReviewSuccessCode.OK;

        return APIResponse.onSuccess(code, resDTO);
    }

    // 내가 쓴 리뷰 조회
    @GetMapping("/users/reviews")
    public APIResponse<ReviewResponseDTO.Pagination<ReviewResponseDTO.GetMyReview>> getMyReviews(
            @RequestBody ReviewRequestDTO.GetMyReview dto,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam String idCursor,
            @RequestParam String starCursor,
            @RequestParam String query
            ){

        ReviewResponseDTO.Pagination<ReviewResponseDTO.GetMyReview> reviews =
                reviewService.getMyReviews(dto, pageSize, idCursor, starCursor, query);

        BaseSuccessCode code = ReviewSuccessCode.OK;

        return APIResponse.onSuccess(code, reviews);
    }
}
