package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public static class CreateReviewResultDTO {

        private Long reviewId;
        private Long memberMissionId;
        private Double star;
        private String content;
        private LocalDateTime createdAt;

        public CreateReviewResultDTO(
                Long reviewId,
                Long memberMissionId,
                Double star,
                String content,
                LocalDateTime createdAt
        ) {
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

    public static class MyReviewListResponseDTO {

        private List<MyReviewDTO> reviews;
        private CursorInfoDTO cursorInfo;

        public MyReviewListResponseDTO(
                List<MyReviewDTO> reviews,
                CursorInfoDTO cursorInfo
        ) {
            this.reviews = reviews;
            this.cursorInfo = cursorInfo;
        }

        public List<MyReviewDTO> getReviews() {
            return reviews;
        }

        public CursorInfoDTO getCursorInfo() {
            return cursorInfo;
        }
    }

    public static class MyReviewDTO {

        private Long reviewId;
        private Long storeId;
        private String storeName;
        private Double star;
        private String content;
        private LocalDateTime createdAt;

        public MyReviewDTO(
                Long reviewId,
                Long storeId,
                String storeName,
                Double star,
                String content,
                LocalDateTime createdAt
        ) {
            this.reviewId = reviewId;
            this.storeId = storeId;
            this.storeName = storeName;
            this.star = star;
            this.content = content;
            this.createdAt = createdAt;
        }

        public Long getReviewId() {
            return reviewId;
        }

        public Long getStoreId() {
            return storeId;
        }

        public String getStoreName() {
            return storeName;
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

    public static class CursorInfoDTO {

        private Long nextCursorId;
        private Double nextCursorStar;
        private Integer size;
        private Boolean hasNext;
        private String sortType;

        public CursorInfoDTO(
                Long nextCursorId,
                Double nextCursorStar,
                Integer size,
                Boolean hasNext,
                String sortType
        ) {
            this.nextCursorId = nextCursorId;
            this.nextCursorStar = nextCursorStar;
            this.size = size;
            this.hasNext = hasNext;
            this.sortType = sortType;
        }

        public Long getNextCursorId() {
            return nextCursorId;
        }

        public Double getNextCursorStar() {
            return nextCursorStar;
        }

        public Integer getSize() {
            return size;
        }

        public Boolean getHasNext() {
            return hasNext;
        }

        public String getSortType() {
            return sortType;
        }
    }
}