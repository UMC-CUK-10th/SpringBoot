package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;

public class MemberFoodConverter {

    // 멤버 선호 음식 추가
    public static MemberFood toMemberFood(
            Member member,
            Food food
    ){
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }
}
