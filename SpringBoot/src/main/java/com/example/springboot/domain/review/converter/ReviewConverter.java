package com.example.springboot.domain.review.converter;

import com.example.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.domain.review.entity.Review;
import com.example.springboot.domain.users.dto.UsersResDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static UsersResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return UsersResDTO.ReviewPreViewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getStoreName())
                .nickname(review.getUsers().getNickname())
                .favorite(review.getFavorite())
                .content(review.getReviewContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static UsersResDTO.ReviewListDTO toReviewListDTO(List<Review> reviewList, boolean isFirst, boolean isLast) {
        List<UsersResDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());

        Long lastId = reviewList.isEmpty() ? null : reviewList.get(reviewList.size() - 1).getId();
        Integer lastFavorite = reviewList.isEmpty() ? null : reviewList.get(reviewList.size() - 1).getFavorite();

        return UsersResDTO.ReviewListDTO.builder()
                .isLast(isLast)
                .isFirst(isFirst)
                .reviewList(reviewPreViewDTOList)
                .listSize(reviewPreViewDTOList.size())
                .lastId(lastId)
                .lastFavorite(lastFavorite)
                .build();
    }

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
