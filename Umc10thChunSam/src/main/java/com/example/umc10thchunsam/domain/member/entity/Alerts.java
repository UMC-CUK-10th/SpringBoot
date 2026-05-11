package com.example.umc10thchunsam.domain.member.entity;

import com.example.umc10thchunsam.domain.member.entity.mapping.MemberAlert;
import com.example.umc10thchunsam.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "alerts")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Alerts extends BaseEntity {

    @OneToMany(mappedBy ="alerts")
    private List<MemberAlert> memberAlert = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alerts_id")
    private Long id;

    @Column(name = "alerts", nullable = false)
    private String alert;

}
