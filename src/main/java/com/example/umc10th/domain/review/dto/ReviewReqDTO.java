package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public static class CreateReviewDTO {

        @NotNull
        private Long memberMissionId;

        @NotNull
        @DecimalMin("0.5")
        @DecimalMax("5.0")
        private Double star;

        @NotBlank
        private String content;

        public Long getMemberMissionId() {
            return memberMissionId;
        }

        public Double getStar() {
            return star;
        }

        public String getContent() {
            return content;
        }
    }
}