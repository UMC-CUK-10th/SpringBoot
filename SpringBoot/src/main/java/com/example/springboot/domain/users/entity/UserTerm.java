package com.example.springboot.domain.users.entity;

import com.example.springboot.domain.term.entity.Term;
import com.example.springboot.domain.users.entity.enums.UserTermAgree;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_term")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_term_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_term_agree", nullable = false, columnDefinition = "VARCHAR(20)")
    private UserTermAgree userTermAgree;

    @CreatedDate
    @Column(name = "user_term_agree_date", updatable = false)
    private LocalDateTime userTermAgreeDate;
}
