package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Address name;

    protected Location() {
    }

    public Location(Address name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Address getName() {
        return name;
    }
}