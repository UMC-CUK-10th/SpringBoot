package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record MyReviewCursorRequest(
            @NotNull(message = "userId는 필수입니다.")
            Long userId,

            Long cursorId,

            Float cursorScore,

            @NotNull(message = "size는 필수입니다.")
            @Min(value = 1, message = "size는 1 이상이어야 합니다.")
            Integer size
    ) {
    }
}