package com.example.umc10th.domain.members.repository;

import com.example.umc10th.domain.members.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {
    boolean existsByEmail(String email);

    Optional<Members> findByEmail(String email);
}