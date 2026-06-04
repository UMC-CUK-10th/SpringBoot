package com.example.springboot.domain.users.repository;

import com.example.springboot.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findBySocialTypeAndSocialUid(com.example.springboot.domain.users.entity.enums.SocialType socialType, String socialUid);
    Optional<Users> findByUsername(String name);
}