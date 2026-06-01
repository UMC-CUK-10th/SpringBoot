package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    private String title;
    private String description;
    private String missionContent;
    private Integer reward;
    private LocalDate deadline;
    private Long regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();

    protected Mission() {
    }

    public Long getId() {
        return missionId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMissionContent() {
        return missionContent != null ? missionContent : description;
    }

    public Integer getReward() {
        return reward;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Long getRegionId() {
        return regionId;
    }

    public Store getStore() {
        return store;
    }

    public List<MemberMission> getMemberMissions() {
        return memberMissions;
    }

    public Integer getPoint() {
        return reward;
    }

    public String getConditional() {
        return getMissionContent();
    }
}
