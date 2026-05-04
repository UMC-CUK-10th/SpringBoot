package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.MALE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;

    @Column(name = "email", columnDefinition = "TEXT", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_num", length = 15, nullable = false)
    private String phone_num;

    @Column(name = "point")
    private int point;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "inactiveDate")
    private LocalDateTime inactiveDate;
}
