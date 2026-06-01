package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.converter.MemberFoodConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원 가입
    @Transactional
    public MemberResponseDTO.CreateInfo joinMember(
            MemberRequestDTO.CreateInfo dto
    ){

        // 아이디 중복 시 예외 처리
        if (memberRepository.existsByUsername(dto.username())){
            throw new MemberException(MemberErrorCode.DUPLICATE_USERNAME);
        }
        // 이메일 중복 시 예외 처리
        else if(memberRepository.existsByEmail(dto.email())){
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 BCrypt 솔트 처리
        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);
        memberRepository.save(member);

        // 선호 음식 저장
        List<MemberFood> memberFoods = dto.foodIds().stream()
                .map(foodId -> {
                    Food food = foodRepository.findById(foodId)
                            .orElseThrow(() -> new ProjectException(GeneralErrorCode.FOOD_NOT_FOUND));
                    return MemberFoodConverter.toMemberFood(member,food);
                })
                .collect(Collectors.toList());

        memberFoodRepository.saveAll(memberFoods);

        return MemberConverter.toCreateInfo(member);
    }

    // 내 정보 조회
    public MemberResponseDTO.GetInfo getInfo
    (
            MemberRequestDTO.GetInfo dto
    ) {
        Member member =  memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }


    // 마이 페이지
    public MemberResponseDTO.MyPage getMyPage (
            AuthMember member
    ){

        return MemberConverter.toMyPage(member.getMember());
    }

    // 로그인
    public MemberResponseDTO.LoginInfo login(
            MemberRequestDTO.LoginInfo dto
    ){
        Member member = memberRepository.findByEmail(dto.username())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID_CREDENTIALS);
        }

        AuthMember authMember = new AuthMember(member);
        String token = jwtUtil.createAccessToken(authMember);

        return MemberConverter.toLoginInfo(token);
    }



    // 홈 화면
//    public MemberResponseDTO.Home getHome(
//            MemberRequestDTO.Home dto,
//            Long localId,
//            int page,
//            int size
//    ) {
//        Member member = memberRepository.findById(dto.id())
//                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
//
//        Local local = localRepository.findById(localId)
//                        .orElseThrow();
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<Mission> missions = missionRepository.findByLocal(local, pageable);
//
//        Page<MissionResponseDTO.Ho> missionDTOs = missions.map(mission ->
//                MissionConverter.toGetInfo(mission,null));
//
//        return MemberConverter.toHome(member,missionDTOs);
//    }
}
