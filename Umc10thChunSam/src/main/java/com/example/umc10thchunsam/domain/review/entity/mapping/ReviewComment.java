package com.example.umc10thchunsam.domain.review.entity.mapping;

import com.example.umc10thchunsam.domain.review.entity.Review;
import com.example.umc10thchunsam.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "review_comment")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Long responseId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="review_id")
    private Review review;  // FK
}
