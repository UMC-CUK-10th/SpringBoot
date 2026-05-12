package com.example.springboot10th.domain.store.controller;

import com.example.springboot10th.domain.store.dto.ReviewRequestDTO;
import com.example.springboot10th.domain.store.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> createReview(
            @PathVariable("storeId") Long storeId,
            @RequestParam("userId") Long userId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {
        
        reviewService.createReview(userId, storeId, request);
        return ResponseEntity.ok("리뷰 작성 완료");
    }
}
