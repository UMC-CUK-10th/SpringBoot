package com.example.umc10thchunsam.domain.member.entity;


import com.example.umc10thchunsam.domain.member.entity.mapping.MemberAlert;
import com.example.umc10thchunsam.domain.member.entity.mapping.MemberMission;
import com.example.umc10thchunsam.domain.member.entity.mapping.MemberPreferenceFood;
import com.example.umc10thchunsam.domain.member.entity.mapping.MemberPromise;
import com.example.umc10thchunsam.domain.member.enums.Gender;
import com.example.umc10thchunsam.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member {


    @OneToMany(mappedBy ="member", fetch = FetchType.LAZY)
    private List<MemberPromise> memberPromise = new ArrayList<>();

    @OneToMany(mappedBy ="member", fetch = FetchType.LAZY)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy ="member", fetch = FetchType.LAZY)
    private List<MemberAlert> memberAlerts = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberPreferenceFood> memberPreferenceFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();


    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "userid",nullable = false)
    private String userId;

    @Column(name = "password",nullable = false)
    private String password;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.None;

    @Column(name = "birthdate",nullable = false)
    private LocalDate birthDate;

    @Column(name = "adress",nullable = false)
    private String adress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name="phone",nullable = false)
    private String phone;

    @Column(name ="point")
    private int point;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name ="status")
    private String status;

    @Column(name= "vaildate")
    private Date vaildate;

}
