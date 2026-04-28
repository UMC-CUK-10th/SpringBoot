package com.example.springboot.domain.mission.entity;

import com.example.springboot.domain.mission.entity.enums.MissionStatus;
import com.example.springboot.domain.store.entity.Store;
import com.example.springboot.domain.users.entity.UserMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "mission_name", nullable = false, length = 15)
    private String missionName;

    @Column(name = "mission_content", length = 15)
    private String missionContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_status", nullable = false, columnDefinition = "VARCHAR(20)")
    private MissionStatus missionStatus;

    @Column(name = "mission_point", nullable = false)
    private Long missionPoint;

    @Column(name = "mission_end_date")
    private LocalDateTime missionEndDate;

    // 연관관계
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();
}
