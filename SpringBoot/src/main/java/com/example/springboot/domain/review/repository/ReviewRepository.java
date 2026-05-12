package com.example.springboot.domain.review.repository;

import com.example.springboot.domain.review.entity.Review;
import com.example.springboot.domain.users.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1. ID 순 (DESC)
    @Query("SELECT r FROM Review r WHERE r.users = :user AND (:lastId IS NULL OR r.id < :lastId) ORDER BY r.id DESC")
    Slice<Review> findByUsersAndIdLessThanOrderByIdDesc(@Param("user") Users user, @Param("lastId") Long lastId, Pageable pageable);

    // 2. 별점 순 (DESC)
    @Query("SELECT r FROM Review r WHERE r.users = :user AND (:lastFavorite IS NULL OR r.favorite < :lastFavorite OR (r.favorite = :lastFavorite AND (:lastId IS NULL OR r.id < :lastId))) ORDER BY r.favorite DESC, r.id DESC")
    Slice<Review> findByUsersByFavoriteCursor(@Param("user") Users user, @Param("lastFavorite") Integer lastFavorite, @Param("lastId") Long lastId, Pageable pageable);
}
