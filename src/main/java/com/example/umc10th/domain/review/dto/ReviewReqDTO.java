package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    // 리뷰 쓰기
    public record CreateReview(

            @NotNull
            Long storeId,

            @NotBlank
            String content,

            @Min(1)
            @Max(5)
            int rating

    ) {}
}