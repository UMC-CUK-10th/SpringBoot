package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/my/id")
    public ReviewResDTO.MyReviewCursorResponse getMyReviewsOrderById(
            @RequestBody @Valid ReviewReqDTO.MyReviewCursorRequest request
    ) {
        return reviewService.getMyReviewsOrderById(request);
    }

    @PostMapping("/my/score")
    public ReviewResDTO.MyReviewCursorResponse getMyReviewsOrderByScore(
            @RequestBody @Valid ReviewReqDTO.MyReviewCursorRequest request
    ) {
        return reviewService.getMyReviewsOrderByScore(request);
    }
}