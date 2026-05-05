package com.example.umc10thchunsam.domain.review.controller;

import com.example.umc10thchunsam.domain.review.dto.ReviewOfferListResponse;
import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.domain.review.dto.req.ReviewCreateReq;
import com.example.umc10thchunsam.domain.review.dto.req.ReviewGetReq;
import com.example.umc10thchunsam.domain.review.dto.res.ReviewCreateRes;
import com.example.umc10thchunsam.domain.review.dto.res.ReviewPageRes;
import com.example.umc10thchunsam.domain.review.repo.ReviewQueryService;
import com.example.umc10thchunsam.domain.review.service.ReviewService;
import com.example.umc10thchunsam.global.anotation.PageUnderOne;
import com.example.umc10thchunsam.global.apiPayload.ApiResponse;
import com.example.umc10thchunsam.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//wsl2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;


    @PostMapping
    public ApiResponse<ReviewCreateRes> saveReview(@RequestBody ReviewCreateReq.ReviewCreateRequest request) {

        ReviewCreateRes res = reviewService.ReviewCreate(request);

        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS,res);
    }

    @GetMapping("/serach")
    public ApiResponse<ReviewOfferListResponse> searchReviews(
            @RequestParam String query,
            @RequestParam String type,
            @PathVariable String id
    ) {

        List<ReviewOfferResponse> result= reviewQueryService.searchReviews(query,type,id);
        ReviewOfferListResponse response = new ReviewOfferListResponse(result);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS,response);

    }



    @Operation(
            summary = "내가 가진 리뷰들을 페이지 단위로 가져오기",
            description = "페이지네이션으로 제공합니다."
    )

    @GetMapping("/member/{memberId}")
    public ApiResponse<ReviewPageRes> GetReviewsByMemberId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") @PageUnderOne int page
    ) {

        ReviewGetReq.ReviewGetByMeberIdReq req = new ReviewGetReq.ReviewGetByMeberIdReq(id);
        ReviewPageRes res = reviewService.GetReviewsByMemberId(req ,page);


        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS,res);


    }


}
