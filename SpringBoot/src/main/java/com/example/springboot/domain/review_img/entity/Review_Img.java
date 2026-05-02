package com.example.springboot.domain.review_img.entity;

import com.example.springboot.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_img")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review_Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "review_url", nullable = false, length = 150)
    private String reviewUrl;
}
