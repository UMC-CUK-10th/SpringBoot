package com.example.umc10thchunsam.domain.review.service;


import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.member.repo.MemberRepository;
import com.example.umc10thchunsam.domain.review.Exception.ReviewException;
import com.example.umc10thchunsam.domain.review.Exception.code.ReviewErrorCode;
import com.example.umc10thchunsam.domain.review.converter.ReviewConverter;
import com.example.umc10thchunsam.domain.review.dto.ReviewOfferListResponse;
import com.example.umc10thchunsam.domain.review.dto.req.ReviewCreateReq;
import com.example.umc10thchunsam.domain.review.dto.req.ReviewGetReq;
import com.example.umc10thchunsam.domain.review.dto.res.ReviewCreateRes;
import com.example.umc10thchunsam.domain.review.dto.res.ReviewPageRes;
import com.example.umc10thchunsam.domain.review.entity.Review;
import com.example.umc10thchunsam.domain.review.repo.ReviewRepository;
import com.example.umc10thchunsam.domain.store.entity.Restourant;
import com.example.umc10thchunsam.domain.store.repo.RestourantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestourantRepository restourantRepository;


    public ReviewCreateRes ReviewCreate(ReviewCreateReq.ReviewCreateRequest request) {


        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.No_Mem_EXCEPTION)); // 맴버 있는지 확인

        Restourant restourant = restourantRepository.findById(request.restaurantId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.No_Resto_EXCEPTION)); // 가게 있는지 확인

        Review review = ReviewConverter.toEntity(request, member, restourant); // 리뷰 객체 생성

        try {
            reviewRepository.save(review);
        } catch (Exception e) {
            throw new ReviewException(ReviewErrorCode.SAVE_FAILED_EXCEPTION);
        }

        return new ReviewCreateRes(true);
    }

    public ReviewPageRes GetReviewsByMemberId(ReviewGetReq.ReviewGetByMeberIdReq req, int page) {

        PageRequest pageable = getStartPage(page);
        Page<Review> reviewList = reviewRepository.findByMemberIdWithFetchJoin(req.getMemberId(),pageable);


        return ReviewConverter.toPageResponse(reviewList);


    }



    //페이지 만들기
    private PageRequest getStartPage(int page) {
        final int Limit_Size = 10;
        int pageIndex = page - 1;

        return PageRequest.of(pageIndex, Limit_Size);
    }


}
