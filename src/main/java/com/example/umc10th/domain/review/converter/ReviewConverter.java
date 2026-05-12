package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

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
                .restaurantId(review.getRestaurant().getId())
                .memberId(review.getMember().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 리뷰 조회 API
    public static ReviewResDTO.GetReviewDTO toGetReviewDTO(Review review) {
        return ReviewResDTO.GetReviewDTO.builder()
                .reviewId(review.getId())
                .restaurantId(review.getRestaurant().getId())
                .memberId(review.getMember().getId())
                .grade(review.getGrade())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 페이지네이션 틀 생성 (커서 기반 페이지네이션)
    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
