package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findReviewByRestaurant_IdOrderByIdDesc(Long restaurantId, PageRequest pageRequest);

    Slice<Review> findReviewByRestaurant_IdAndIdLessThanOrderByIdDesc(Long restaurantId, Long id, PageRequest pageRequest);

    Slice<Review> findReviewByRestaurant_IdOrderByGradeDescIdDesc(Long restaurantId, PageRequest pageRequest);

    @Query("""
            select r
            from Review r
            where r.restaurant.id = :restaurantId
              and (r.grade < :grade or (r.grade = :grade and r.id < :id))
            order by r.grade desc, r.id desc
            """)
    Slice<Review> findReviewsByGradeCursor(
            @Param("restaurantId") Long restaurantId,
            @Param("grade") Integer grade,
            @Param("id") Long id,
            PageRequest pageRequest
    );
}
