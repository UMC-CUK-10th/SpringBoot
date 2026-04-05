package com.example.umc_spring.domain.mission.entity.mapping;

import com.example.umc_spring.domain.member.entity.Member;
import com.example.umc_spring.domain.mission.entity.Mission;
import com.example.umc_spring.domain.mission.entity.Restaurant;
import com.example.umc_spring.domain.review.entity.PointHistory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "mission_execution_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionExecutionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_execution_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(name = "user_mission_id", nullable = false)
    private Long userMissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "executed_at", nullable = false)
    private LocalDateTime executedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "missionExecutionHistory")
    private List<PointHistory> pointHistories = new ArrayList<>();
}
