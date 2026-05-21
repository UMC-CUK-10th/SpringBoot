package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 기존의 리뷰 생성 로직 (데이터가 변경되므로 @Transactional 덮어쓰기)
    @Transactional
    public Long createReview(ReviewReqDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReview(request, member, store);

        return reviewRepository.save(review).getId();
    }

    // 새로 추가된 커서 기반 페이징 로직
    public ReviewResDTO.CursorResult<ReviewResDTO.MyReviewListDTO> getMyReviews(Long memberId, Long cursorId, BigDecimal cursorStar, String sortBy, int size) {
        // 다음 페이지가 있는지 확인하기 위해 size + 1 개를 조회합니다.
        Pageable pageable = PageRequest.of(0, size + 1);
        List<Review> reviews;

        if ("STAR".equalsIgnoreCase(sortBy)) {
            reviews = reviewRepository.findMyReviewsByStarCursor(memberId, cursorStar, cursorId, pageable);
        } else {
            reviews = reviewRepository.findMyReviewsByIdCursor(memberId, cursorId, pageable);
        }

        boolean hasNext = reviews.size() > size;
        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        List<ReviewResDTO.MyReviewListDTO> dtoList = reviews.stream()
                .map(ReviewConverter::toMyReviewListDTO)
                .collect(Collectors.toList());

        Long nextCursorId = null;
        BigDecimal nextCursorStar = null;

        // 데이터가 있고 다음 페이지가 존재하면 다음 커서를 계산합니다.
        if (hasNext && !dtoList.isEmpty()) {
            ReviewResDTO.MyReviewListDTO lastItem = dtoList.get(dtoList.size() - 1);
            nextCursorId = lastItem.getReviewId();
            nextCursorStar = lastItem.getStar();
        }

        return new ReviewResDTO.CursorResult<>(dtoList, nextCursorId, nextCursorStar, hasNext);
    }
}