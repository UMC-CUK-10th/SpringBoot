package com.example.umc10th.domain.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDTO {

    public static class ReviewWritePageDTO {
        Long storeId;
        String storeName;
    }

    public static class MyReviewListDTO {
        List<MyReviewViewDTO> reviewList;
        Integer listSize;
    }

    public static class MyReviewViewDTO {
        Long reviewId;
        String storeName;
        Float rating;
        String contents;
        String createdAt;
    }
}
