package com.example.umc10thchunsam.domain.review.repo;

import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {


    List<ReviewOfferResponse> searchReviews(Predicate predicate);


}
