package com.example.umc_spring.domain.member.entity;

import com.example.umc_spring.domain.member.entity.mapping.MemberFavoriteFood;
import com.example.umc_spring.domain.member.enums.Gender;
import com.example.umc_spring.domain.member.enums.Role;
import com.example.umc_spring.domain.member.enums.SocialType;
import com.example.umc_spring.domain.mission.entity.mapping.MissionExecutionHistory;
import com.example.umc_spring.domain.mission.entity.UserMission;
import com.example.umc_spring.domain.review.entity.PointHistory;
import com.example.umc_spring.domain.review.entity.Review;
import com.example.umc_spring.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "user_birth")
    private LocalDate birth;

    @Column(name = "user_address", length = 255, nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false)
    private SocialType socialType;

    @Column(name = "social_uid", length = 255, nullable = false)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Integer point;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member")
    private List<MemberFavoriteFood> memberFavoriteFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MissionExecutionHistory> missionExecutionHistories = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PointHistory> pointHistories = new ArrayList<>();
}