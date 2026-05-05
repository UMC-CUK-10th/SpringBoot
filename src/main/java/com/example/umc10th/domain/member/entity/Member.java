package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.*;


@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();
}
