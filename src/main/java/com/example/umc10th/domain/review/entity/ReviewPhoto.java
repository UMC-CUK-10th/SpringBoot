package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review_photo")
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_photo_id")
    private Long id;

    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    protected ReviewPhoto() {
    }

    public ReviewPhoto(String photoUrl, Review review) {
        this.photoUrl = photoUrl;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Review getReview() {
        return review;
    }
}