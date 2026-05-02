package com.example.springboot.domain.food.entity;

import com.example.springboot.domain.users.entity.UserFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "food_name", nullable = false, length = 15)
    private String foodName;

    @Column(name = "food_content", length = 15)
    private String foodContent;

    // 연관관계
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserFood> userFoodList = new ArrayList<>();
}
