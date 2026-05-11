package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {

    public static class CreateReviewResultDTO {

        private Long reviewId;
        private Long memberMissionId;
        private Double star;
        private String content;
        private LocalDateTime createdAt;

        public CreateReviewResultDTO(Long reviewId, Long memberMissionId, Double star, String content, LocalDateTime createdAt) {
            this.reviewId = reviewId;
            this.memberMissionId = memberMissionId;
            this.star = star;
            this.content = content;
            this.createdAt = createdAt;
        }

        public Long getReviewId() {
            return reviewId;
        }

        public Long getMemberMissionId() {
            return memberMissionId;
        }

        public Double getStar() {
            return star;
        }

        public String getContent() {
            return content;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}