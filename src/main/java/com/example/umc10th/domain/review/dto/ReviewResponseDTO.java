package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    // 리뷰 작성
    @Builder
    public record CreateReview(

            String content,
            Float star,
            LocalDateTime createdAt,
            String name,
            String storeName

    ){}

    // 내가 쓴 리뷰 조회
    @Builder
    public record GetMyReview(
            Long id,
            String content,
            Float star
    ){}


    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
