package com.example.umc10th.domain.members.entity;

import com.example.umc10th.domain.members.enums.Gender;
import com.example.umc10th.domain.members.entity.mapping.MemberPreferences;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "members")
public class Members extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(length = 50)
    private String email;
    private Integer totalPoint;

    @Column(length = 20)
    private String phoneNumber;

    private Boolean isVerified;

    @Column(nullable = false, length = 100)
    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<MemberPreferences> memberPreferenceList = new ArrayList<>();

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}