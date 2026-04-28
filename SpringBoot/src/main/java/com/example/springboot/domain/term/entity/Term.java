package com.example.springboot.domain.term.entity;

import com.example.springboot.domain.term.entity.enums.TermNecessity;
import com.example.springboot.domain.users.entity.UserTerm;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "term")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "term_title", nullable = false, length = 15)
    private String termTitle;

    @Column(name = "term_content", nullable = false, columnDefinition = "TEXT")
    private String termContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_necessity", nullable = false, columnDefinition = "VARCHAR(20)")
    private TermNecessity termNecessity;

    @Column(name = "term_version", length = 15)
    private String termVersion;

    @CreatedDate
    @Column(name = "term_created_at", updatable = false)
    private LocalDateTime termCreatedAt;

    // 연관관계
    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserTerm> userTermList = new ArrayList<>();
}
