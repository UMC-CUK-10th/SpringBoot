package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class MemberMission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;

    private String status; // 진행중 / 완료
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
}