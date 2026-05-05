package com.example.umc10th.domain.member.entity.mapping;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(
        name = "member_food",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "food_id"})
        }
)
public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    protected MemberFood() {
    }

    public MemberFood(Member member, Food food) {
        this.member = member;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Food getFood() {
        return food;
    }
}