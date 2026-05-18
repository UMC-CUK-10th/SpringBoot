package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {


    // 리뷰 작성
    public static ReviewResponseDTO.CreateReview toCreateReview(
            Review review
    ) {

        return ReviewResponseDTO.CreateReview.builder()
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .name(review.getMember().getName())
                .storeName(review.getStore().getName())
                .build();
    }

    // 내가 쓴 리뷰 조회
    public static ReviewResponseDTO.GetMyReview toGetMyReview(
            Review review
    ){
        return ReviewResponseDTO.GetMyReview.builder()
                .id(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }

    // 페이지네이션
    public static <T> ReviewResponseDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean haseNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResponseDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(haseNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
