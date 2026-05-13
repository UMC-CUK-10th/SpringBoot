package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성하는 쿼리
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewReqDTO.CreateReviewDTO request) {
        Long reviewId = reviewService.createReview(request);
        return ResponseEntity.ok("리뷰가 성공적으로 작성되었습니다. Review ID: " + reviewId);
    }
}