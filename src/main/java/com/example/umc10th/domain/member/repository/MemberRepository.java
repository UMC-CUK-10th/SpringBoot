package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 6주차 예제 - 회원 조회
    // 메서드 이름으로 쿼리 생성
    Optional<Member> findByNameAndInactiveDateIsNull(String name);

    /*
    // @Query 어노테이션
    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.inactiveDate IS NULL")
    Optional<Member> findActiveMember(String name);
     */
}
