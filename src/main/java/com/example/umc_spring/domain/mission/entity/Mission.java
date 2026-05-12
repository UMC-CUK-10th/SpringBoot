package com.example.umc_spring.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_condition", nullable = false)
    private String missionCondition;

    @Column(name = "reward_point")
    private Integer reward;

    @Column(name = "mission_title")
    private String missionTitle;

    @Column(name = "mission_target_count")
    private Integer missionTargetCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}