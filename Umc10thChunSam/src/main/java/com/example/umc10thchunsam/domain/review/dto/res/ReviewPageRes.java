package com.example.umc10thchunsam.domain.review.dto.res;

import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.global.PageInfo;

import java.util.List;

public record ReviewPageRes(
        List<ReviewOfferResponse> reviews,
        PageInfo pageInfo
) {}