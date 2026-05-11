package com.example.umc10th.domain.member.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private com.example.umc10th.domain.member.enums.Term name;

    protected Term() {
    }

    public Term(com.example.umc10th.domain.member.enums.Term name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public com.example.umc10th.domain.member.enums.Term getName() {
        return name;
    }
}