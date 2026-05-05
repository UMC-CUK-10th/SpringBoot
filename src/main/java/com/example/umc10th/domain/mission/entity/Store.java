package com.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "manager_number")
    private Long managerNumber;

    @Column(name = "detail_address", nullable = false, length = 255)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    protected Store() {
    }

    public Store(String name, Long managerNumber, String detailAddress, Location location) {
        this.name = name;
        this.managerNumber = managerNumber;
        this.detailAddress = detailAddress;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getManagerNumber() {
        return managerNumber;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public Location getLocation() {
        return location;
    }
}