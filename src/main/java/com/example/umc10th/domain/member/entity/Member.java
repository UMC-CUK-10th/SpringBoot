package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address")
    @Enumerated(EnumType.STRING)
    private Address address;

    // --- 여기서부터 첫 번째 이미지(Converter)를 위해 추가된 필드들입니다 ---

    @Column(name = "email")
    private String email;

    @Column(name = "point")
    private Integer point; // 포인트는 보통 Integer 자료형을 사용합니다.

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_url")
    private String profileUrl;
}