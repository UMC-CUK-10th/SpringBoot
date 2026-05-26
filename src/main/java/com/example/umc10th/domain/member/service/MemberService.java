package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MemberTermRepository memberTermRepository;
    private final PasswordEncoder passwordEncoder;

    // 마이 페이지
    public MemberResDTO.ProfileDTO getMyProfile(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toProfileDTO(member);
    }

    // 회원 가입
    @Transactional
    public MemberResDTO.JoinResultDTO join(MemberReqDTO.JoinDTO request) {
        // 이메일 중복 확인
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 비밀번호 BCrypt 인코딩
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Member 엔티티 생성 및 저장
        Member member = MemberConverter.toMember(request, encodedPassword);
        memberRepository.save(member);

        // 선호 음식 매핑 저장
        if (request.getPreferredFoodIds() != null && !request.getPreferredFoodIds().isEmpty()) {
            List<Food> foods = foodRepository.findAllById(request.getPreferredFoodIds());
            if (foods.size() != request.getPreferredFoodIds().size()) {
                throw new MemberException(MemberErrorCode.INVALID_FOOD_ID);
            }
            List<MemberFood> memberFoods = foods.stream()
                    .map(food -> MemberConverter.toMemberFood(member, food))
                    .toList();
            memberFoodRepository.saveAll(memberFoods);
        }

        // 약관 동의 매핑 저장
        if (request.getAgreedTermIds() != null && !request.getAgreedTermIds().isEmpty()) {
            List<Term> terms = termRepository.findAllById(request.getAgreedTermIds());
            if (terms.size() != request.getAgreedTermIds().size()) {
                throw new MemberException(MemberErrorCode.INVALID_TERM_ID);
            }
            List<MemberTerm> memberTerms = terms.stream()
                    .map(term -> MemberConverter.toMemberTerm(member, term))
                    .toList();
            memberTermRepository.saveAll(memberTerms);
        }

        return MemberConverter.toJoinResultDTO(member);
    }
}
