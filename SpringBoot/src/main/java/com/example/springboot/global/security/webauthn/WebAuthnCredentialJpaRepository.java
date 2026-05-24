package com.example.springboot.global.security.webauthn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebAuthnCredentialJpaRepository extends JpaRepository<WebAuthnCredential, String> {
    List<WebAuthnCredential> findByUserEntityUserId(String userEntityUserId);
}
