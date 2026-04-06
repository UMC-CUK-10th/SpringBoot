package com.example.umc10thchunsam.domain.member.repo;

import com.example.umc10thchunsam.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByUserId(String username);

    boolean existsByUserId(String username);


}
