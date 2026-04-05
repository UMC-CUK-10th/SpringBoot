package com.example.umc_spring.domain.member.entity;

import com.example.umc_spring.domain.member.entity.mapping.MemberFavoriteFood;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "favorite_food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_food_id")
    private Long id;

    @Column(name = "favorite_food_category", nullable = false, length = 30)
    private String category;

    @OneToMany(mappedBy = "favoriteFood")
    private List<MemberFavoriteFood> memberFavoriteFoods = new ArrayList<>();
}
