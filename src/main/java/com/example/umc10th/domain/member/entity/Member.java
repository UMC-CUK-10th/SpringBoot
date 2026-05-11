package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.entity.mapping.MissionUser;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "total_point", nullable = false)
    @Builder.Default
    private Integer totalPoints = 0;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MissionUser> missionUserList = new ArrayList<>();
}
