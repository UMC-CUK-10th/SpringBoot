package com.example.umc_spring.domain.member.entity.mapping;

import com.example.umc_spring.domain.member.entity.FavoriteFood;
import com.example.umc_spring.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_favorite_food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFavoriteFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_favorite_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_food_id")
    private FavoriteFood favoriteFood;
}
