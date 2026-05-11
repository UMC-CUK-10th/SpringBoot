package com.example.umc10thchunsam.domain.review.entity;
import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.review.entity.mapping.ReviewComment;
import com.example.umc10thchunsam.domain.store.entity.Restourant;
import com.example.umc10thchunsam.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "review")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Review extends BaseEntity {

    @OneToMany (mappedBy = "review")
    private List<ReviewComment> reviewComment= new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "star", nullable = false) //최소 1개
    private Float star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restourant restourant;   // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;         // FK
}
