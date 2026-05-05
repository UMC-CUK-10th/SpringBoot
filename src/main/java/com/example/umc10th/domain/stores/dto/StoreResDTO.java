package com.example.umc10th.domain.stores.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class StoreResDTO {
    public static class StoreListDTO {
        List<StoreSummaryDTO> storeList;
        Integer listSize;
    }

    public static class StoreSummaryDTO {
        Long storeId;
        String name;
        String address;
        Float rating;
    }

    public static class StoreDetailDTO {
        Long storeId;
        String name;
        String address;
        String hours;
        Float rating;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        List<ReviewViewDTO> reviewList;
        Integer listSize;
    }

    public static class ReviewViewDTO {
        Long reviewId;
        String userName;
        Float rating;
        String contents;
        String createdAt;
    }
}
