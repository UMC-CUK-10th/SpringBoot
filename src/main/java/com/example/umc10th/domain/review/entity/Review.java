package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.member.entity.Member;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String content;
    private float rating;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Long missionId;

    public void setMember(Member member) {
    }

    public void setMissionId(Long missionId) {
    }

    public void setContent(String content) {
    }

    public void setRating(float rating) {
    }
}