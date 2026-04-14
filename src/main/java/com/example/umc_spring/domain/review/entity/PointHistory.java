package com.example.umc_spring.domain.review.entity;

import com.example.umc_spring.domain.member.entity.Member;
import com.example.umc_spring.domain.mission.entity.mapping.MissionExecutionHistory;
import com.example.umc_spring.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "point_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_execution_history_id")
    private MissionExecutionHistory missionExecutionHistory;

    @Column(name = "point_delta", nullable = false)
    private Long pointDelta;
}
