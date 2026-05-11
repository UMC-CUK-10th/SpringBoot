package com.example.umc10th.domain.review.service.command;

import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {

    // 리뷰 작성 API
    ReviewResDTO.CreateReviewDTO createReview(Long restId, Long memberId, ReviewReqDTO.CreateReviewDTO dto);
}
