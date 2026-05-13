package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

public class ReviewReqDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private Long memberId;
        private Long storeId;
        private String content;
        private BigDecimal star;
    }
}