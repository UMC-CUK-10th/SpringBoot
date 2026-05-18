package com.example.umc10th.domain.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewWritePageDTO {
        Long storeId;
        String storeName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewListDTO {
        List<MyReviewViewDTO> reviewList;
        Integer listSize;
        Long lastId;           // 다음에 요청할 때 사용할 커서 (ID)
        Float lastRating;      // 다음에 요청할 때 사용할 커서 (별점)
        Boolean isLast;        // 마지막 페이지 여부
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewViewDTO {
        Long reviewId;
        String storeName;
        Float rating;
        String contents;
        String createdAt;
    }
}
