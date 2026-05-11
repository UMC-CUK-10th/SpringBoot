package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import jakarta.persistence.*;

@Entity
@Table(
        name = "member_mission",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "mission_id"})
        }
)
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @Column(name = "is_complete", nullable = false)
    private Boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected MemberMission() {
    }

    public MemberMission(Member member, Mission mission) {
        this.member = member;
        this.mission = mission;
        this.isComplete = false;
    }

    public void complete() {
        this.isComplete = true;
    }

    public Long getId() {
        return id;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public Mission getMission() {
        return mission;
    }

    public Member getMember() {
        return member;
    }
}