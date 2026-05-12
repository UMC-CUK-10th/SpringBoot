package com.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.umc10th.domain.store.entity.Store;

@Entity
@Getter
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward;

    private String content;

    // 어떤 가게의 미션인지
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}