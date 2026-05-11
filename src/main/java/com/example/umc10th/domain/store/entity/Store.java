package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.mapping.StoreMission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private Local local;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<StoreMission> storeMissionList = new ArrayList<>();
}
