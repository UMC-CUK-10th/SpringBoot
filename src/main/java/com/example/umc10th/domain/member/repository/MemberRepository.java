package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 6주차 예제 - 회원 조회
    // 메서드 이름으로 쿼리 생성
    Optional<Member> findByNameAndInactiveDateIsNull(String name);

    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);
    boolean existsByEmailAndSocialType(String email, SocialType socialType);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
    boolean existsBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
}
