package com.example.umc10thchunsam.domain.review.dto.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewCreateReq {

    public record ReviewCreateRequest(
            Long memberId,
            Long restaurantId,
            Float star,
            String content
    ) {}

}
