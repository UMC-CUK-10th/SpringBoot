package com.example.umc10thchunsam.domain.review.converter;


import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.review.dto.ReviewOfferListResponse;
import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.domain.review.dto.req.ReviewCreateReq;
import com.example.umc10thchunsam.domain.review.dto.res.ReviewPageRes;
import com.example.umc10thchunsam.domain.review.entity.Review;
import com.example.umc10thchunsam.domain.store.entity.Restourant;
import com.example.umc10thchunsam.global.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static Review toEntity(ReviewCreateReq.ReviewCreateRequest req,
                                  Member member,
                                  Restourant restourant) {

        return Review.builder()
                .member(member)
                .restourant(restourant)
                .star(req.star())
                .content(req.content())
                .build();
    }


    public static ReviewOfferResponse toResponse(Review r) {
        return ReviewOfferResponse.builder()
                .restaurantName(r.getRestourant().getRestaurantName())
                .name(r.getMember().getName())
                .star(r.getStar())
                .content(r.getContent())
                .createdAt(r.getCreateAt())
                .updatedAt(r.getUpdateAt())
                .build();
    }


    public static List<ReviewOfferResponse> toResponses(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toResponse)
                .toList();
    }

    public static ReviewOfferListResponse toListResponse(List<Review> reviews) {
        return new ReviewOfferListResponse(
                toResponses(reviews)
        );
    }



    public static ReviewPageRes toPageResponse(Page<Review> page) {

        List<ReviewOfferResponse> reviewDtos =
                page.getContent().stream()
                        .map(ReviewConverter::toResponse)
                        .toList();

        PageInfo pageInfo = new PageInfo(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );

        return new ReviewPageRes(reviewDtos, pageInfo);
    }
}
