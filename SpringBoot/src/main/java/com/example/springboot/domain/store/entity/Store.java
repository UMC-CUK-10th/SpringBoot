package com.example.springboot.domain.store.entity;

import com.example.springboot.domain.mission.entity.Mission;
import com.example.springboot.domain.region.entity.Region;
import com.example.springboot.domain.review.entity.Review;
import com.example.springboot.domain.store.entity.enums.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "store_name", nullable = false, length = 15)
    private String storeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "store_status", nullable = false, columnDefinition = "VARCHAR(20)")
    private StoreStatus storeStatus;

    @Column(name = "store_address", length = 15)
    private String storeAddress;

    @Column(name = "store_phone_number", length = 15)
    private String storePhoneNumber;

    @Column(name = "category", length = 15)
    private String category;

    // 연관관계
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}
