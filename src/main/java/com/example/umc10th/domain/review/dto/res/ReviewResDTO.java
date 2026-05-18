package com.example.umc10th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성 DTO
    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            Long restaurantId,
            Long memberId,
            LocalDateTime createdAt
    ) {}

    // 리뷰 조회 DTO
    @Builder
    public record GetReviewDTO(
            Long reviewId,
            Long restaurantId,
            Long memberId,
            Integer grade,
            String comment,
            LocalDateTime createdAt
    ) {}

    // 페이지네이션 틀 (커서 기반 페이지네이션)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
