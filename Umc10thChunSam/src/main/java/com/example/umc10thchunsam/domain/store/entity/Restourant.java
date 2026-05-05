package com.example.umc10thchunsam.domain.store.entity;

import com.example.umc10thchunsam.domain.mission.entity.Mission;
import com.example.umc10thchunsam.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "restourant")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Restourant {

    @OneToMany(mappedBy = "restourant")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restourant")
    private List<Mission> missions = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_adress", nullable = false) // ERD 표기 그대로
    private String restaurantAdress;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private Location location;  // FK to location
}
