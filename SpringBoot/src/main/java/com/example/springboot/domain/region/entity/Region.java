package com.example.springboot.domain.region.entity;

import com.example.springboot.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(name = "region_name", nullable = false, length = 15)
    private String regionName;

    @Column(name = "region_address", length = 15)
    private String regionAddress;

    // 연관관계
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();
}
