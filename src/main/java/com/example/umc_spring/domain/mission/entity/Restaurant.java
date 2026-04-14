package com.example.umc_spring.domain.mission.entity;

import com.example.umc_spring.domain.mission.entity.mapping.MissionExecutionHistory;
import com.example.umc_spring.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "restaurant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name", length = 20, nullable = false)
    private String name;

    @Column(name = "restaurant_location", length = 255, nullable = false)
    private String location;

    @Column(name = "owner_number", length = 25, nullable = false)
    private String ownerNumber;

    @OneToMany(mappedBy = "restaurant")
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<MissionExecutionHistory> missionExecutionHistories = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>();
}
