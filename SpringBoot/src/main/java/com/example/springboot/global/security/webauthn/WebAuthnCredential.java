package com.example.springboot.global.security.webauthn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "webauthn_credentials")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WebAuthnCredential {

    @Id
    @Column(name = "credential_id", length = 512)
    private String credentialId;

    @Column(name = "user_entity_user_id", nullable = false, length = 128)
    private String userEntityUserId;

    @Lob
    @Column(name = "public_key", nullable = false)
    private byte[] publicKey;

    @Column(name = "signature_count", nullable = false)
    private long signatureCount;

    @Column(name = "uv_initialized", nullable = false)
    private boolean uvInitialized;

    @Column(name = "backup_eligible", nullable = false)
    private boolean backupEligible;

    @Column(name = "backup_state", nullable = false)
    private boolean backupState;

    @Column(name = "authenticator_transports", length = 255)
    private String authenticatorTransports;

    @Column(name = "public_key_credential_type", nullable = false, length = 32)
    private String publicKeyCredentialType;

    @Lob
    @Column(name = "attestation_object")
    private byte[] attestationObject;

    @Lob
    @Column(name = "attestation_client_data_json")
    private byte[] attestationClientDataJson;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "last_used")
    private Instant lastUsed;

    @Column(name = "label", length = 100)
    private String label;
}
