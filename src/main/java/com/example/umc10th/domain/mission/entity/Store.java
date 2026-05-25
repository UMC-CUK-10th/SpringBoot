package com.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;


@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Location location;

    public Long getId() {
        return storeId;
    }

    public String getName() {
        return name;
    }
}
