package com.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    private LocalDate deadline;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conditional;

    @Column(nullable = false)
    private Integer reward;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    protected Mission() {
    }

    public Mission(LocalDate deadline, String conditional, Integer reward, Store store) {
        this.deadline = deadline;
        this.conditional = conditional;
        this.reward = reward;
        this.store = store;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getConditional() {
        return conditional;
    }

    public Integer getReward() {
        return reward;
    }

    public Store getStore() {
        return store;
    }
}