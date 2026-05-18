package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReviewRequestDTO {

    // 리뷰 작성
    public record CreateReview(
            @NotBlank(message = "리뷰 내용은 필수 입니다.")
            String content,
            @NotNull(message = "리뷰 별점은 필수 입니다.")
            Float star,
            LocalDateTime cratedAt,
            Long userId,
            Long storeId

    ){}

    // 내가 쓴 리뷰 조회
    public record GetMyReview(
            Long userId
    ){}
}
