package com.example.springboot.domain.review.converter;

import com.example.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.WriteResultDTO toWriteResultDTO(Review review) {
        return ReviewResDTO.WriteResultDTO.builder()
                .review_id(review.getId())
                .user_id(review.getUsers().getId())
                .store_id(review.getStore().getId())
                .store_name(review.getStore().getStoreName())
                .nickname(review.getUsers().getNickname())
                .review_content(review.getReviewContent())
                .favorite(review.getFavorite())
                .created_at(review.getCreatedAt())
                .build();
    }
}
