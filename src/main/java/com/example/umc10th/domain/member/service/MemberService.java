package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final PasswordEncoder passwordEncoder;

    // 5주차 예제 - 마이페이지 API
    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.SignUpDTO signUp(MemberReqDTO.SignUpDTO dto) {
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        List<Food> foods = foodRepository.findAllById(dto.preferCategory());
        if (foods.size() != dto.preferCategory().size()) {
            throw new MemberException(MemberErrorCode.FOOD_NOT_FOUND);
        }

        Member member = MemberConverter.toMember(dto, passwordEncoder.encode(dto.password()));
        Member savedMember = memberRepository.save(member);

        List<MemberFood> memberFoods = foods.stream()
                .map(food -> MemberFood.builder()
                        .member(savedMember)
                        .food(food)
                        .build())
                .toList();
        memberFoodRepository.saveAll(memberFoods);

        return MemberConverter.toSignUpDTO(savedMember);
    }
}
