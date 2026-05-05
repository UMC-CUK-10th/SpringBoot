package com.example.umc10th.domain.reviews.entity;

import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.stores.entity.Stores;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "reviews")
public class Reviews extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private Float rating;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(columnDefinition = "TEXT")
    private String answer;

    private Boolean isAnswered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Members members;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Stores stores;
}