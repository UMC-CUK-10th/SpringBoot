package com.example.umc10thchunsam.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewOfferListResponse {
    List<ReviewOfferResponse> list;
}
