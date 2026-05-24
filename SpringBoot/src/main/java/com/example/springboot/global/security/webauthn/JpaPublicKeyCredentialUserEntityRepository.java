package com.example.springboot.global.security.webauthn;

import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.ImmutablePublicKeyCredentialUserEntity;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialUserEntity;
import org.springframework.security.web.webauthn.management.PublicKeyCredentialUserEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaPublicKeyCredentialUserEntityRepository implements PublicKeyCredentialUserEntityRepository {

    private final UsersRepository usersRepository;

    @Override
    public PublicKeyCredentialUserEntity findById(Bytes id) {
        Long userId = WebAuthnUserHandleUtil.toUserId(id);
        if (userId == null) {
            return null;
        }
        return usersRepository.findById(userId)
                .map(this::toUserEntity)
                .orElse(null);
    }

    @Override
    public PublicKeyCredentialUserEntity findByUsername(String username) {
        return usersRepository.findByEmail(username)
                .or(() -> usersRepository.findByUsername(username))
                .map(this::toUserEntity)
                .orElse(null);
    }

    @Override
    public void save(PublicKeyCredentialUserEntity userEntity) {
        // Users is already managed by the application domain.
    }

    @Override
    public void delete(Bytes id) {
        // Deleting an application user is outside the passkey repository's responsibility.
    }

    private PublicKeyCredentialUserEntity toUserEntity(Users users) {
        return ImmutablePublicKeyCredentialUserEntity.builder()
                .id(WebAuthnUserHandleUtil.fromUserId(users.getId()))
                .name(users.getEmail())
                .displayName(users.getNickname())
                .build();
    }
}
