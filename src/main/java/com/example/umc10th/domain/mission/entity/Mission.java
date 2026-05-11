package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MissionUser;
import com.example.umc10th.domain.store.entity.Local;
import com.example.umc10th.domain.store.entity.mapping.StoreMission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "mission_point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private Local local;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    private List<MissionUser> missionUserList = new ArrayList<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    private List<StoreMission> storeMissionList = new ArrayList<>();

}
