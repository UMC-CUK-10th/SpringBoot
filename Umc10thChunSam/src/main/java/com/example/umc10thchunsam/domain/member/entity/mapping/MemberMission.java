package com.example.umc10thchunsam.domain.member.entity.mapping;

import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.mission.entity.Mission;
import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Table(name = "member_mission") // ERD의 Member_mission
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_success")
    private MissionStatus issuccess;   // BEFORE / IN_PROGRESS / COMPLETED

    @Column(name = "success_date")
    private LocalDate successDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="mission_id")// ERD의 약관ID(FK)
    private Mission mission;      // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")// ERD의 약관ID(FK)
    private Member member;   // FK
}
