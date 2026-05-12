package com.example.umc10th.domain.review.service.command;

import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {

    // 리뷰 작성 API
    ReviewResDTO.CreateReviewDTO createReview(Long restaurantId, Long memberId, ReviewReqDTO.CreateReviewDTO dto);

    // 리뷰 조회 API
    ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO> getReviews(Long restaurantId, Integer pageSize, String cursor, String query);
}
