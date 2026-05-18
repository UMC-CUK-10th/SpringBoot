package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findReviewByMember_IdAndIdLessThanOrderByIdDesc(
            Long id,
            Long idCursor,
            PageRequest pageRequest
    );

    Slice<Review> findReviewByMember_IdAndStarLessThanEqualOrderByStarDescIdDesc(
            Long id,
            Float starCursor,
            Long idCursor,
            PageRequest pageRequest
    );

    Slice<Review> findReviewByMember_IdOrderByIdDesc(
            Long id,
            PageRequest pageRequest
    );
}
