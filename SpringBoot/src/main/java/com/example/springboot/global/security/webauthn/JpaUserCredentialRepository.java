package com.example.springboot.global.security.webauthn;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.webauthn.api.AuthenticatorTransport;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.CredentialRecord;
import org.springframework.security.web.webauthn.api.ImmutableCredentialRecord;
import org.springframework.security.web.webauthn.api.ImmutablePublicKeyCose;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialType;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaUserCredentialRepository implements UserCredentialRepository {

    private final WebAuthnCredentialJpaRepository repository;

    @Override
    @Transactional
    public void delete(Bytes credentialId) {
        repository.deleteById(credentialId.toBase64UrlString());
    }

    @Override
    @Transactional
    public void save(CredentialRecord record) {
        WebAuthnCredential credential = WebAuthnCredential.builder()
                .credentialId(record.getCredentialId().toBase64UrlString())
                .userEntityUserId(record.getUserEntityUserId().toBase64UrlString())
                .publicKey(record.getPublicKey().getBytes())
                .signatureCount(record.getSignatureCount())
                .uvInitialized(record.isUvInitialized())
                .backupEligible(record.isBackupEligible())
                .backupState(record.isBackupState())
                .authenticatorTransports(toTransportString(record.getTransports()))
                .publicKeyCredentialType(record.getCredentialType().getValue())
                .attestationObject(toBytes(record.getAttestationObject()))
                .attestationClientDataJson(toBytes(record.getAttestationClientDataJSON()))
                .created(record.getCreated() == null ? Instant.now() : record.getCreated())
                .lastUsed(record.getLastUsed())
                .label(record.getLabel())
                .build();

        repository.save(credential);
    }

    @Override
    public CredentialRecord findByCredentialId(Bytes credentialId) {
        return repository.findById(credentialId.toBase64UrlString())
                .map(this::toCredentialRecord)
                .orElse(null);
    }

    @Override
    public java.util.List<CredentialRecord> findByUserId(Bytes userId) {
        return repository.findByUserEntityUserId(userId.toBase64UrlString())
                .stream()
                .map(this::toCredentialRecord)
                .collect(Collectors.toList());
    }

    private CredentialRecord toCredentialRecord(WebAuthnCredential credential) {
        return ImmutableCredentialRecord.builder()
                .credentialId(Bytes.fromBase64(credential.getCredentialId()))
                .userEntityUserId(Bytes.fromBase64(credential.getUserEntityUserId()))
                .publicKey(new ImmutablePublicKeyCose(credential.getPublicKey()))
                .signatureCount(credential.getSignatureCount())
                .uvInitialized(credential.isUvInitialized())
                .backupEligible(credential.isBackupEligible())
                .backupState(credential.isBackupState())
                .transports(toTransportSet(credential.getAuthenticatorTransports()))
                .credentialType(PublicKeyCredentialType.valueOf(credential.getPublicKeyCredentialType()))
                .attestationObject(toBytes(credential.getAttestationObject()))
                .attestationClientDataJSON(toBytes(credential.getAttestationClientDataJson()))
                .created(credential.getCreated())
                .lastUsed(credential.getLastUsed())
                .label(credential.getLabel())
                .build();
    }

    private String toTransportString(Set<AuthenticatorTransport> transports) {
        if (transports == null || transports.isEmpty()) {
            return "";
        }
        return transports.stream()
                .map(AuthenticatorTransport::getValue)
                .sorted()
                .collect(Collectors.joining(","));
    }

    private Set<AuthenticatorTransport> toTransportSet(String transports) {
        if (transports == null || transports.isBlank()) {
            return Set.of();
        }
        return Arrays.stream(transports.split(","))
                .filter(value -> !value.isBlank())
                .map(AuthenticatorTransport::valueOf)
                .collect(Collectors.toSet());
    }

    private byte[] toBytes(Bytes bytes) {
        return bytes == null ? null : bytes.getBytes();
    }

    private Bytes toBytes(byte[] bytes) {
        return bytes == null ? null : new Bytes(bytes);
    }
}
