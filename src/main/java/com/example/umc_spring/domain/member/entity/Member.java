package com.example.umc_spring.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "user_birth")
    private Integer userBirth;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "social_provider")
    private String socialProvider;

    @Column(name = "social_uid")
    private String socialUid;
}