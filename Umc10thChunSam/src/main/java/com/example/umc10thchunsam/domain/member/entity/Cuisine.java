package com.example.umc10thchunsam.domain.member.entity;

import com.example.umc10thchunsam.domain.member.entity.mapping.MemberPreferenceFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "cuisine") // ERD 표기가 'cusine'
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "cuisine")
    private List<MemberPreferenceFood> memberPreferenceFoodList = new ArrayList<>();


    @Column(name = "cuisines", nullable = false)
    private String cuisine;   // Enum 대신 문자열로 둠(값 범위 미정)
}
