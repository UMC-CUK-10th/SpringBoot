package com.example.umc10th.domain.member.entity.mapping;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import jakarta.persistence.*;

@Entity
@Table(
        name = "member_terms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "term_id"})
        }
)
public class MemberTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_term_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

    protected MemberTerm() {
    }

    public MemberTerm(Member member, Term term) {
        this.member = member;
        this.term = term;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Term getTerm() {
        return term;
    }
}