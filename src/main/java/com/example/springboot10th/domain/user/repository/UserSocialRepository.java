package com.example.springboot10th.domain.user.repository;

import com.example.springboot10th.domain.user.entity.UserSocial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSocialRepository extends JpaRepository<UserSocial, Long> {

    @org.springframework.data.jpa.repository.Query("select us from UserSocial us join fetch us.user where us.provider = :provider and us.providerId = :providerId")
    Optional<UserSocial> findByProviderAndProviderId(
            @org.springframework.data.repository.query.Param("provider") String provider,
            @org.springframework.data.repository.query.Param("providerId") String providerId
    );
}
