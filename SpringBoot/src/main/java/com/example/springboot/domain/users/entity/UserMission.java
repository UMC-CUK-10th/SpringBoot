package com.example.springboot.domain.users.entity;

import com.example.springboot.domain.mission.entity.Mission;
import com.example.springboot.domain.users.entity.enums.UserMissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_mission_status", nullable = false, columnDefinition = "VARCHAR(20)")
    private UserMissionStatus userMissionStatus;

    @Column(name = "complete_at")
    private LocalDateTime completeAt;

    @Column(name = "contribution_number", length = 15)
    private String contributionNumber;
}
