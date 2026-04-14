package com.example.umc_spring.domain.mission.entity;

import com.example.umc_spring.domain.mission.entity.mapping.MissionExecutionHistory;
import com.example.umc_spring.domain.mission.entity.UserMission;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_condition", length = 255, nullable = false)
    private String missionCondition;

    @Column(name = "reward_point", nullable = false)
    private Long rewardPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "mission_title", length = 50, nullable = false)
    private String title;

    @Column(name = "mission_target_count", nullable = false)
    private Long targetCount;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "mission")
    private List<MissionExecutionHistory> missionExecutionHistories = new ArrayList<>();
}
