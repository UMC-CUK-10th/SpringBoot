package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    // 리뷰 작성 API
    public static Review toEntity(Member member, Restaurant restaurant, ReviewReqDTO.CreateReviewDTO dto) {
        return Review.builder()
                .member(member)
                .restaurant(restaurant)
                .grade(dto.getGrade())
                .comment(dto.getComment())
                .build();
    }

    public static ReviewResDTO.CreateReviewDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .restId(review.getRestaurant().getId())
                .memberId(review.getMember().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
