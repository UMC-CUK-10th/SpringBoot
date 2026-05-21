package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewDTO request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                .star(request.getStar())
                .build();
    }

    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Review review) {
        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

