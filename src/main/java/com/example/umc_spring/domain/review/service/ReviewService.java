package com.example.umc_spring.domain.review.service;

import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;

public interface ReviewService {

    ReviewResDTO.MyReviewCursorDTO getMyReviewsOrderById(
            ReviewReqDTO.MyReviewCursorRequestDTO request
    );

    ReviewResDTO.MyReviewCursorDTO getMyReviewsOrderByStar(
            ReviewReqDTO.MyReviewCursorRequestDTO request
    );
}