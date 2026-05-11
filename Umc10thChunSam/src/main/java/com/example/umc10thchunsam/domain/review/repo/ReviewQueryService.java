package com.example.umc10thchunsam.domain.review.repo;

import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.domain.review.entity.QReview;
import com.example.umc10thchunsam.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
//type에넌 star, store
    public List<ReviewOfferResponse> searchReviews(String query, String type, String id) {

        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.id.eq(Long.parseLong(id)));


        if (type.equals("store")) {
            builder.and(review.restourant.restaurantName.contains(query));
        }

        if (type.equals("star")) { // start의 쿼리로는 1,2,3,4,5를 받음 query<=x<query+1
            builder.and(review.star.goe(Float.parseFloat(query)))
                    .and(review.star.lt(Float.parseFloat(query)+1));
        }

        List<ReviewOfferResponse> reviewList = reviewRepository.searchReviews(builder);

        return reviewList;
    }
}
