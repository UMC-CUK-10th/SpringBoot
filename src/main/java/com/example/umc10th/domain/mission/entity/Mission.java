package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    private String title;
    private String description;

    private Long regionId;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();
}