package com.example.umc10th.domain.review.service.command;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    // 리뷰 작성 API
    @Override
    @Transactional
    public ReviewResDTO.CreateReviewDTO createReview(Long restId, Long memberId, ReviewReqDTO.CreateReviewDTO dto) {

        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new ProjectException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review saved = reviewRepository.save(ReviewConverter.toEntity(member, restaurant, dto));

        return ReviewConverter.toCreateDTO(saved);
    }

    // 리뷰 조회 API
    @Override
    public ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO> getReviews(
            Long restaurantId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewList;

        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findReviewByRestaurant_IdAndIdLessThanOrderByIdDesc(
                            restaurantId,
                            idCursor,
                            pageRequest
                    );
                    break;
                case "score":
                case "grade":
                    Integer gradeCursor = Integer.parseInt(cursorSplit[0]);
                    Long reviewIdCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findReviewsByGradeCursor(
                            restaurantId,
                            gradeCursor,
                            reviewIdCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ProjectException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            switch (query.toLowerCase()) {
                case "id":
                    reviewList = reviewRepository.findReviewByRestaurant_IdOrderByIdDesc(restaurantId, pageRequest);
                    break;
                case "score":
                case "grade":
                    reviewList = reviewRepository.findReviewByRestaurant_IdOrderByGradeDescIdDesc(restaurantId, pageRequest);
                    break;
                default:
                    throw new ProjectException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        String nextCursor = "-1";
        if (!reviewList.isEmpty()) {
            Review lastReview = reviewList.getContent().get(reviewList.getContent().size() - 1);
            nextCursor = switch (query.toLowerCase()) {
                case "id" -> lastReview.getId() + ":" + lastReview.getId();
                case "score", "grade" -> lastReview.getGrade() + ":" + lastReview.getId();
                default -> throw new ProjectException(ReviewErrorCode.QUERY_NOT_VALID);
            };
        }

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReviewDTO).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
