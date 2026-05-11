package com.example.umc10thchunsam.domain.review.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReviewOfferResponse {


    //레스토랑에서 가져올것
    private String restaurantName;


    //맴버에서 가져올것
    private String name;

    //리뷰에서 가져올것
    private Float star;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ReviewOfferResponse(String restaurantName, String name, Float star, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.restaurantName = restaurantName;
        this.name = name;
        this.star = star;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
