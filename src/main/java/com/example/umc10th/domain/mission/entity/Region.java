package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "regionName", length = 20, nullable = false)
    private String regionName;

    @OneToMany(mappedBy = "region")
    private List<Restaurant> restaurants = new ArrayList<>();
}
