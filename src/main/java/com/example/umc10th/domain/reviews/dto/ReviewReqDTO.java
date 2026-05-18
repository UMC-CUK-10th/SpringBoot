package com.example.umc10th.domain.reviews.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
            Float rating,
            @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
            @Size(min = 10, message = "리뷰는 최소 10자 이상 작성해주세요.")
            String contents,
            String image_url,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {}
}
