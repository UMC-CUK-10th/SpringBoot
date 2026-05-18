package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 생성
    public ReviewResponseDTO.CreateReview createReview(
            ReviewRequestDTO.CreateReview dto
    ) {

        Review review = Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(memberRepository.findById(dto.userId())
                        .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)))
                .store(storeRepository.findById(dto.storeId())
                        .orElseThrow(()-> new StoreException(StoreErrorCode.STORE_NOT_FOUND)))
                .build();

        reviewRepository.save(review);

        return ReviewConverter.toCreateReview(review);
    }

    // 내가 쓴 리뷰 조회 (id 순, 별점 순)
    public ReviewResponseDTO.Pagination<ReviewResponseDTO.GetMyReview> getMyReviews(
            ReviewRequestDTO.GetMyReview dto,
            Integer pageSize,
            String idCursor,
            String starCursor,
            String query
    ){

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursorValue;
        float starCursorValue;
        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if(!idCursor.equals("-1")) {

            // id 커서 분리
            String[] idCursorSplit = idCursor.split(":");

            // id 커서 타입 변환
            Long prevIdCursor = Long.parseLong(idCursorSplit[0]);
            idCursorValue = Long.parseLong(idCursorSplit[1]);

            switch (query.toLowerCase()){

                case "id":
                    // 가게 내 미션들 조회 & where 절에 커서 값 기입
                    reviewList = reviewRepository.findReviewByMember_IdAndIdLessThanOrderByIdDesc(
                            dto.userId(),
                            idCursorValue,
                            pageRequest
                    );
                    break;

                case "star":

                    // star 커서 분리
                    String[] starCursorSplit = starCursor.split(":");

                    // star 커서 타입 변환
                    Float prevStarCursorValue = Float.parseFloat(starCursorSplit[0]);
                    starCursorValue = Float.parseFloat(starCursorSplit[1]);

                    reviewList = reviewRepository.findReviewByMember_IdAndStarLessThanEqualOrderByStarDescIdDesc(
                            dto.userId(),
                            starCursorValue,
                            idCursorValue,
                            pageRequest

                    );
                    break;


                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }
        else {
            // 커서 없이 조회
            reviewList = reviewRepository.findReviewByMember_IdOrderByIdDesc(
                    dto.userId(),
                    pageRequest
            );
        }

        // 다음 커서 계산
        List<Review> content = reviewList.getContent();
        Long lastId = content.get(content.size() - 1).getId();
        nextCursor = lastId + ":" + lastId;

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetMyReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
