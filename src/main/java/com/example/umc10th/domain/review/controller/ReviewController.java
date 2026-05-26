package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // [수정됨] @Valid 어노테이션 추가
    @PostMapping
    public ResponseEntity<String> createReview(@Valid @RequestBody ReviewReqDTO.CreateReviewDTO request) {
        Long reviewId = reviewService.createReview(request);
        return ResponseEntity.ok("리뷰가 성공적으로 작성되었습니다. Review ID: " + reviewId);
    }

    // [추가됨] 내가 생성한 리뷰들 조회하기 (커서 기반)
    @GetMapping("/my-reviews/{memberId}")
    public ResponseEntity<ReviewResDTO.CursorResult<ReviewResDTO.MyReviewListDTO>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) BigDecimal cursorStar,
            @RequestParam(defaultValue = "ID") String sortBy, // "ID" 또는 "STAR"
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(reviewService.getMyReviews(memberId, cursorId, cursorStar, sortBy, size));
    }
}