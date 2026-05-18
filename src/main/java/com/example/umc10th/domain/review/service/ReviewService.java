package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public interface ReviewService {

    /**
     * 과제 2: 내가 작성한 리뷰 목록 조회 (커서 기반 페이지네이션)
     * - ID순(최신순) / 별점순 지원
     */
    ReviewResDTO.ReviewListRes getMyReviews(ReviewReqDTO.GetReviewListReq request);
}