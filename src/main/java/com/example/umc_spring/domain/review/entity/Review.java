package com.example.umc_spring.domain.review.entity;

import com.example.umc_spring.domain.member.entity.Member;
import com.example.umc_spring.domain.mission.entity.Restaurant;
import com.example.umc_spring.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(name = "review_content", length = 500, nullable = false)
    private String content;

    @Column(name = "review_score", nullable = false)
    private Long score;

    @Column(length = 500, nullable = false)
    private String comment;
}
