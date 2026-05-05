package com.example.umc10thchunsam.domain.review.repo;

import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewQueryDsl {



    @Query("""
    select rv from Review rv
    join fetch rv.member
    join fetch rv.restourant
    where rv.member.id = :id
""")
    Page<Review> findByMemberIdWithFetchJoin(Long id, Pageable pageable);





}
