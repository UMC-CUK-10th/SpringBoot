package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class ReviewReqDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5 이하여야 합니다.")
        private BigDecimal star;
    }
}